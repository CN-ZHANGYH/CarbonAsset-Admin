pragma solidity ^0.4.25;
pragma experimental ABIEncoderV2;

import "./CarbonDataStorage.sol"; 

contract CarbonUserService is CarbonDataStorage{
    
    event UserRegistered(address _enterpriseAddress,uint256 _registerTime);
    
    /**
     * @dev 注册用户
     * 
     */
     function registerEnterprise(address _enterpriseAddress,string memory _enterpriseName) public returns(int,Enterprise){
         int res_code = 0;
         userIdQueryAddress[EnterpriseID] = _enterpriseAddress;
         Enterprise storage _enterprise = EnterprisesMap[_enterpriseAddress];
         // TODO: 已完成已注册用户的校验
         if (_enterprise.enterpriseAddress == address(0)){
             EnterpriseIDList.push(EnterpriseID);
             _enterprise.enterpriseId = EnterpriseID;
             _enterprise.enterpriseAddress = _enterpriseAddress;
             _enterprise.enterpriseName = _enterpriseName;
             _enterprise.enterpriseBalance = 0;
             _enterprise.enterpriseTotalEmission = 0;
             _enterprise.enterpriseOverEmission = 0;
             _enterprise.enterpriseCarbonCredits = 0;
             _enterprise.enterpriseVerified = false;
             _enterprise.userType = 1;
             _enterprise.qualificationId = EnterpriseID;
             EnterpriseID++;
             emit UserRegistered(_enterpriseAddress,block.timestamp);
             return (res_code = 200,_enterprise);
         }
         _enterprise = EnterprisesMap[address(0)];
         return(res_code = 60001,_enterprise);
    }
    
    /**
     * @dev 注册监管机构
     * 
     */
    function registerRegulator(address _regulatorAddress,string memory _regulatorName) public returns(int,Regulator){
        int res_code = 0;
        Regulator storage _regulator = RegulatorsMap[_regulatorAddress];
        // TODO: 已完成已注册机构的校验
        if (_regulator.regulatorAddress == address(0)){
            RegulatorIDList.push(RegulatorID);
            _regulator.regulatorId = RegulatorID;
            _regulator.regulatorAddress = _regulatorAddress;
            _regulator.regulatorName = _regulatorName;
            _regulator.userType = 2;
            RegulatorID++;
            emit UserRegistered(_regulatorAddress,block.timestamp);
            return(res_code = 200,_regulator);
        }
        _regulator = RegulatorsMap[address(0)];
        return(res_code = 60001,_regulator);
    }
    
    
    /**
     * @dev 企业上传资质
     * 
     */
     function uploadQualification(
         address _enterpriseAddress,
         string memory _qualificationName,
         string memory _qualificationContent,
         string memory _qualificationLeader,
         string memory _qualificationIndustry,
         string memory _qualificationUserName
    ) public returns(int,string memory,Qualification)
    {
        // TODO: 后端查询当前的资质是否已经上传
        int res_code = 0;
        Enterprise storage _enterprise = EnterprisesMap[_enterpriseAddress];
        Qualification storage _qualification = QualificationsMap[_enterprise.qualificationId];
        if (_enterprise.qualificationId == 0){
            return(res_code = 60004,"当前的企业未注册",_qualification);
        }
        QualificationIDList.push( _enterprise.qualificationId);
        _qualification.qualificationId = _enterprise.qualificationId;
        _qualification.qualificationName = _qualificationName;
        _qualification.qualificationContent = _qualificationContent;
        _qualification.qualificationLeader = _qualificationLeader;
        _qualification.qualificationIndustry = _qualificationIndustry;
        _qualification.qualificationUserName = _qualificationUserName;
        _qualification.qualificationUploadTime = block.timestamp;
        emit UploadQualification(msg.sender,_qualificationName,_qualificationContent);
        return (res_code = 200,"上传成功",_qualification);
    }
    

    /**
     * @dev 监管机构审核企业资质
     * 
     */    
    function verifyQualification(address _regulatorAddress,address _enterpriseAddress,bool _isApprove) public  returns(int,Qualification){
        int res_code = 0;
        Enterprise storage _enterprise = EnterprisesMap[_enterpriseAddress];
        Qualification storage _qualification = QualificationsMap[_enterprise.qualificationId];
        
        // 判断是否为监管机构地址
        if (msg.sender != _regulatorAddress || _enterpriseAddress != _enterprise.enterpriseAddress){
            return (res_code = 60002,_qualification);
        }
        if (_enterprise.enterpriseVerified){
            return (res_code = 60003,_qualification);
        }
        if (!_isApprove){
            return (res_code = 200,_qualification);
        }
        _enterprise.enterpriseVerified = true;
        _qualification.qualificationVerifiedRegulator = _regulatorAddress;
        _qualification.qualificationAuditTime = block.timestamp;
        _qualification.qualificationEmissionLimit += TOTAL_EMISSION;
        emit VerifyQualification(_enterpriseAddress,TOTAL_EMISSION);
        return (res_code = 200,_qualification);
    }
    
    /**
     * @dev 签到领取积分
     * 
     */
     function signIn(address _enterpriseAddress) public returns(int,uint256){
        require(msg.sender == _enterpriseAddress,"当前用户不是企业");
        EnterprisesMap[_enterpriseAddress].enterpriseCarbonCredits += SIGNIN_CREDIT;
        return (200,SIGNIN_CREDIT);
     }
    
    
    /**
     * @dev 查询用户地址
     * 
     */
    function selectUserAddress(uint256 _enterpriseID) public view returns(int,address) {
        int res_code = 0;
        address _enterpriseAddress = userIdQueryAddress[EnterpriseID];
        if (_enterpriseAddress == address(0)){
            return(res_code = 60004,_enterpriseAddress);
        }
        return (res_code = 200,_enterpriseAddress);
    }
    
    /**
     * @dev 查询企业用户信息
     * 
     */
     function selectEnterpriseInfo(address _enterpriseAddress) public view returns(Enterprise){
         require(EnterprisesMap[_enterpriseAddress].enterpriseAddress != address(0),"当前用户未注册");
         return EnterprisesMap[_enterpriseAddress];
     }
     
    /**
     * @dev 查询企业的资质信息
     * 
     */
     function selectQualificationInfo(address _enterpriseAddress) public returns(int,Qualification){
         int res_code = 0;
         Enterprise memory _enterprise = EnterprisesMap[_enterpriseAddress];
         Qualification memory _qualification = QualificationsMap[_enterprise.qualificationId];
         if (_enterprise.enterpriseAddress == address(0)){
             return(res_code = 60004,_qualification);
         }
         return (res_code = 200,_qualification);
     }
     
    function updateBalance(uint256 _amount) public {
        require(EnterprisesMap[msg.sender].enterpriseAddress != address(0),"当前用户未注册");
        EnterprisesMap[msg.sender].enterpriseBalance += _amount;
    }
}