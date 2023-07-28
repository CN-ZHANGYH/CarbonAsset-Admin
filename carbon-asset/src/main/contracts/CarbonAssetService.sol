pragma solidity ^0.6.10;
pragma experimental ABIEncoderV2;

import "./CarbonUserService.sol";

contract CarbonAssetService is CarbonUserService {
    
    /*
    * @dev 每个月发放额度1000
    *
    */
    function initEmissionLimit(uint256 _qualificationEmissionLimit) public{
        for (uint256 i = 0; i < EnterpriseIDList.length; i++) {
            Enterprise memory _enterprise = EnterprisesMap[userIdQueryAddress[EnterpriseIDList[i]]];
            if (_enterprise.enterpriseVerified){
                QualificationsMap[_enterprise.qualificationId].qualificationEmissionLimit += _qualificationEmissionLimit;
            }
        }
    }
    
    /*
    * @dev 积分奖励
    *
    */
    function initPointsRewards(address[] memory _enterpriseAddressList,uint256 _credited) public {
        uint256 enterpriseLength = _enterpriseAddressList.length;
        for (uint256 i = 0; i < enterpriseLength; ++i) {
            address _enterpriseAddress = _enterpriseAddressList[i];
            if (EnterprisesMap[_enterpriseAddress].enterpriseAddress == _enterpriseAddress) {
                Enterprise storage _enterprise = EnterprisesMap[_enterpriseAddress];
                if (i < 10) {
                    _enterprise.enterpriseCarbonCredits += (_credited * (10 - i));
                } else {
                    _enterprise.enterpriseCarbonCredits += (_credited / 2);
                }
            }
        }
    }

    /*
    * @dev 企业出售碳额度
    * @param _emissionLimitCount 企业的碳额度数量
    * @param _amount 企业出售的单价
    */
    function sellEmissionLimit(uint256 _emissionLimitCount,uint256 _amount) public returns(int,EnterpriseAsset memory){
        int res_code = 0;
        Enterprise memory _enterprise = EnterprisesMap[msg.sender];
        Qualification storage _qualification = QualificationsMap[_enterprise.qualificationId];
        EnterpriseAsset storage _enterpriseAsset = EnterpriseAssetsMap[EnterpriseAssetID];
        if (_qualification.qualificationEmissionLimit < _emissionLimitCount){
            return (res_code = 70001,_enterpriseAsset);
        }
        _enterpriseAsset.assetId = EnterpriseAssetID;
        _enterpriseAsset.enterpriseId = _enterprise.enterpriseId;
        _enterpriseAsset.enterpriseAddress = _enterprise.enterpriseAddress;
        _enterpriseAsset.assetQuantity = _emissionLimitCount;
        _enterpriseAsset.assetAmount = _amount;
        _enterpriseAsset.time = block.timestamp;
        _enterpriseAsset.status = 1;
        EnterpriseAssetIDList.push(EnterpriseAssetID);
        EnterpriseAssetID++;
        
        _qualification.qualificationEmissionLimit -= _emissionLimitCount;
        emit SellEmissionLimit(_emissionLimitCount,_amount);
        return (res_code = 200,_enterpriseAsset);
    }
    
    /**
     *  @dev 企业购买碳排放额度
     *  @param _enterpriseSeller 购买碳排放额度的地址
     *  @param _eassetId 资产ID
     *  @param _quantity 购买碳排放额度的数量
     */
    function buyEmissionLimit(address _enterpriseSeller,uint256 _eassetId,uint256 _quantity) public returns(int,Transaction memory) {
        int res_code = 0;
        Enterprise storage _buyer = EnterprisesMap[msg.sender];
        Enterprise storage _seller =  EnterprisesMap[_enterpriseSeller];
        Qualification storage _qualification = QualificationsMap[_buyer.qualificationId];
        EnterpriseAsset storage _enterpriseAsset = EnterpriseAssetsMap[_eassetId];
        Transaction storage _transaction = TransactionsMap[TransactionID];
        // 余额不足的情况
        if (_buyer.enterpriseBalance < (_quantity * _enterpriseAsset.assetAmount)){
            return (res_code = 70002,_transaction);
        }
        if (_enterpriseAsset.assetQuantity == 0){
            _enterpriseAsset.status = 0;
            return (res_code = 70004,_transaction);
        }
        // 资产数量的计算
        _qualification.qualificationEmissionLimit += _quantity;
        _enterpriseAsset.assetQuantity -= _quantity;
        
        // 金额的交易
        _buyer.enterpriseBalance -= (_quantity * _enterpriseAsset.assetAmount);
        _seller.enterpriseBalance += (_quantity * _enterpriseAsset.assetAmount);
        
        // 交易订单的生成
        _transaction.transactionId = TransactionID;
        _transaction.buyerId = _buyer.enterpriseId;
        _transaction.sellerId = _seller.enterpriseId;
        _transaction.transactionOrderName = "碳额度交易";
        _transaction.transactionBuyAddress = _buyer.enterpriseAddress;
        _transaction.transactionSellAddress = _seller.enterpriseAddress;
        _transaction.transactionTime = block.timestamp;
        _transaction.transactionQuantity = _quantity;
        TransactionIDList.push(TransactionID);
        TransactionID++;
        emit TransferEmissionLimit(_enterpriseSeller,msg.sender,_quantity);
        return (res_code = 200,_transaction);
    }
    
    /**
     *  @dev 企业的碳排放申请  
     *  @param _enterpriseAddr 企业的账户地址
     *  @param _emissionEmission 企业排放的量
     *  @param _description  企业排放的描述
     *  @param _emissionWay 排放的方式
     */
    function uploadEnterpriseEmission(address _enterpriseAddr,uint256 _emissionEmission,string memory _description,string memory _emissionWay) public returns(int,EmissionResource memory) {
        // TODO:
        int res_code = 0;
        EmissionResource storage _emissionResource = EmissionResourcesMap[EmissionResourceID];
        Enterprise memory _enterprise = EnterprisesMap[_enterpriseAddr];
        // 判断企业是否审核通过
        if (!_enterprise.enterpriseVerified){
            return (res_code = 60005,_emissionResource);
        }
        // 判断企业的额度是否足够
        if (QualificationsMap[_enterprise.qualificationId].qualificationEmissionLimit < _emissionEmission){
            return (res_code = 70001,_emissionResource);
        }
        _emissionResource.emissionId = EmissionResourceID;
        _emissionResource.enterpriseId = _enterprise.enterpriseId;
        _emissionResource.enterpriseAddress =  _enterprise.enterpriseAddress;
        _emissionResource.emissions = _emissionEmission;
        _emissionResource.description = _description;
        _emissionResource.emissionWay = _emissionWay;
        EmissionResourceIDList.push(EmissionResourceID);
        EmissionResourceID++;
        emit UploadEnterpriseEmission(msg.sender,_emissionEmission);
        return (res_code = 200,_emissionResource);
        
    }
    
    
    /**
     *  @dev 审批企业的碳排放量
     *  @param _regularAddress 监管机构地址
     *  @param _enterpriseAddr 企业的账户地址
     *  @param _emmissionid 申请ID
     *  @param _isApprove 是否批准
     */
    function verifyEnterpriseEmission(address _regularAddress,address _enterpriseAddr,uint256 _emmissionid,bool _isApprove) public returns(int,bool){
        // TODO:
        int res_code = 0;
        Enterprise storage _enterprise = EnterprisesMap[_enterpriseAddr];
        EmissionResource storage _emissionResource = EmissionResourcesMap[_emmissionid];
        // 判断当前的地址是否为监管机构
        if (msg.sender != _regularAddress){
            return (res_code = 60002,false);
        }
        // 判断当前是否允许通过审批
        if (_isApprove){
            _emissionResource.isApprove = _isApprove;
            QualificationsMap[_enterprise.qualificationId].qualificationEmissionLimit -= _emissionResource.emissions;
            emit VerifyEnterpriseEmission(_regularAddress,_enterpriseAddr,_isApprove);
            return (res_code = 200,_isApprove);
        }else {
            _emissionResource.isApprove = _isApprove;
            return (res_code = 200,_isApprove);
        }
        
    }

    
    /**
     *  @dev 企业的碳排放
     *  @param _enterpriseAddr 企业地址
     *  @param _emmissionid 排放的资源ID
     *  @param _emissionEmission 企业的实际碳排放量
     */
    function enterpriseEmission(address _enterpriseAddr,uint256 _emmissionid,uint256 _emissionEmission) public returns(int,bool){
        // TODO: 更新用户的排放记录
        int res_code = 0;
        Enterprise storage _enterprise = EnterprisesMap[_enterpriseAddr];
        EmissionResource storage _emissionResource = EmissionResourcesMap[_emmissionid];
        Qualification storage _qualification = QualificationsMap[_enterprise.qualificationId];
        if (!_enterprise.enterpriseVerified){
            return (res_code = 60005,false);
        }
        // 判断申请是否通过
        if (!_emissionResource.isApprove){
            return (res_code = 60006,false);
        }
        // 判断是否更新了总排放量
        if (_enterprise.enterpriseTotalEmission == 0){
            return (res_code = 70003,false);
        }
        _enterprise.enterpriseOverEmission += _emissionEmission;
        _emissionResource.emissionTime = block.timestamp;
        emit EnterpriseEmission(msg.sender,_emissionEmission);
        return (res_code = 200,true);
        
    }
    
    /*
    * @dev 分页拆查询企业的交易历史订单信息
    * @param page       查询的页数
    * @param pageSize   查询的每页的数量
    */
    function queryTransactionsByPage(uint256 page,uint256 pageSize) public returns(Transaction[] memory,uint256) {
        require(TransactionIDList.length != 0,"当前没有任何交易历史记录");
        require(page > 0, "页数不能为0");
        uint256 startIndex = (page - 1) * pageSize; // 计算起始索引
        uint256 endIndex = startIndex + pageSize > TransactionIDList.length ? TransactionIDList.length : startIndex + pageSize; // 计算结束索引
        Transaction[] memory transactionArr = new Transaction[](endIndex - startIndex); // 创建每页大小的 Enterprise 数组
        for (uint i = startIndex; i < endIndex; i++){
            transactionArr[i - startIndex] = TransactionsMap[TransactionIDList[i]];
        }
        return (transactionArr,TransactionIDList.length);
    }
    /*
    * @dev 分页拆查询企业碳排放的历史记录
    * @param page       查询的页数
    * @param pageSize   查询的每页的数量
    */
    function queryEmissionResourceByPage(uint256 page,uint256 pageSize) public returns(EmissionResource[] memory,uint256) {
        require(EmissionResourceIDList.length != 0,"当前没有任何交易历史记录");
        require(page > 0, "页数不能为0");
        uint256 startIndex = (page - 1) * pageSize; // 计算起始索引
        uint256 endIndex = startIndex + pageSize > EmissionResourceIDList.length ? EmissionResourceIDList.length : startIndex + pageSize; // 计算结束索引
        EmissionResource[] memory emissionResourceArr = new EmissionResource[](endIndex - startIndex); // 创建每页大小的 Enterprise 数组
        for (uint i = startIndex; i < endIndex; i++){
            emissionResourceArr[i - startIndex] = EmissionResourcesMap[EmissionResourceIDList[i]];
        }
        return (emissionResourceArr,EmissionResourceIDList.length);
    }
    
    /*
    * @dev 分页拆查询企业出售的资产列表
    * @param page       查询的页数
    * @param pageSize   查询的每页的数量
    */
    function queryEnterpriseAssetByPage(uint256 page,uint256 pageSize) public returns(EnterpriseAsset[] memory,uint256) {
        require(EnterpriseAssetIDList.length != 0,"当前没有任何交易历史记录");
        require(page > 0, "页数不能为0");
        uint256 startIndex = (page - 1) * pageSize; // 计算起始索引
        uint256 endIndex = startIndex + pageSize > EnterpriseAssetIDList.length ? EnterpriseAssetIDList.length : startIndex + pageSize; // 计算结束索引
        EnterpriseAsset[] memory enterpriseAssetArr = new EnterpriseAsset[](endIndex - startIndex); // 创建每页大小的 Enterprise 数组
        for (uint i = startIndex; i < endIndex; i++){
            enterpriseAssetArr[i - startIndex] = EnterpriseAssetsMap[EnterpriseAssetIDList[i]];
        }
        return (enterpriseAssetArr,EnterpriseAssetIDList.length);
    }

    /**
     *  @dev 更新总需排放量
     *  @param _emmissionsCount 总排放量
     */
    function updateEnterpriseEmission(uint256 _emmissionsCount) public  returns(int,uint256){
        int res_code = 0;
        if (EnterprisesMap[msg.sender].enterpriseAddress == address(0)){
            return (res_code = 60004,0);
        }
        EnterprisesMap[msg.sender].enterpriseTotalEmission += _emmissionsCount;
        emit UpdateEmissionLimit(msg.sender,_emmissionsCount);
        return (res_code = 200,_emmissionsCount);
    }
    
    /**
     * @dev 积分兑换碳额度
     * 
     */
    function byCreditsExchangedEmission(address _enterpriseAddr,uint256 _credits) public {
        Enterprise storage _enterprise = EnterprisesMap[_enterpriseAddr];
        _enterprise.enterpriseCarbonCredits -= _credits;
        QualificationsMap[_enterprise.qualificationId].qualificationEmissionLimit += _credits;
    }
    
    function selectTransactionInfo(uint256 _transactionId) public view returns(Transaction memory){
        return TransactionsMap[_transactionId];
    }
    
    function selectSellerAssetInfo(uint256 _eassetId) public view returns(EnterpriseAsset memory) {
        return EnterpriseAssetsMap[_eassetId];
    }
    
    function selectEmissionResourceInfo(uint256 _emmissionid) public view returns(EmissionResource memory){
        return EmissionResourcesMap[_emmissionid];
    }
}