pragma solidity ^0.6.10;

// 定义公共的数据结构
contract CarbonDataStorage {
    // 定义一个结构体来表示企业账户信息
    struct Enterprise {
        uint256 enterpriseId;                   // 账户ID
        address enterpriseAddress;              // 账户地址
        string  enterpriseName;                 // 企业名称
        uint256 enterpriseBalance;              // 账户余额
        uint256 enterpriseTotalEmission;        // 总需排放的量
        uint256 enterpriseOverEmission;         // 已完成的排放量
        uint256 enterpriseCarbonCredits;        // 奖励积分
        bool enterpriseVerified;                // 是否通过审核
        uint8 userType;                         // 账户角色
        uint256 qualificationId;                // 资质信息Id
    }

    // 定义一个结构体来表示企业的资质信息
    struct Qualification {
        uint256 qualificationId;                      // 资质ID
        string  qualificationName;                    // 企业名称
        string  qualificationContent;                 // 社会信用代码
        string  qualificationLeader;                  // 法定代表人
        string  qualificationIndustry;                // 所属的行业
        string  qualificationUserName;                // 联系人姓名
        uint256 qualificationUploadTime;              // 上传时间
        uint256 qualificationAuditTime;               // 审核时间
        address qualificationVerifiedRegulator;       // 审核的监管机构地址
        uint256 qualificationEmissionLimit;           // 碳排放额度
    }


    // 定义一个结构体来表示监管部门账户信息
    struct Regulator {
        uint256 regulatorId;            // 账户ID
        address regulatorAddress;       // 账户地址
        string  regulatorName;          // 部门名称
        uint8 userType;                 // 账户类型
    }

    // 定义一个结构体来表示交易订单信息
    struct Transaction {
        uint256 transactionId;               // 订单的交易ID
        uint256 buyerId;                     // 买家ID
        uint256 sellerId;                    // 卖家的ID
        string  transactionOrderName;        // 订单的名字
        address transactionBuyAddress;       // 买家地址
        address transactionSellAddress;      // 卖家地址
        uint256 transactionTime;             // 订单创建时间
        uint256 transactionQuantity;         // 购买碳额度的数量
    }

    // 排放碳资源的结构体
    struct EmissionResource {
        uint256 emissionId;          // 排放资源的ID 
        uint256 enterpriseId;        // 企业的ID
        address enterpriseAddress;   // 排放的企业
        uint256 emissions;           // 排放的量
        string  description;         // 排放的资源描述
        string  emissionWay;         // 排放的方式
        bool    isApprove;           // 是否批准排放
        uint256 emissionTime;        // 排放时间
    }
    
    // 企业出售碳资产的结构体
    struct EnterpriseAsset {
        uint assetId;                   // 企业出售碳资产的列表Id
        uint256 enterpriseId;           // 企业的ID
        address enterpriseAddress;      // 企业出售碳资产的账户地址
        uint256 assetQuantity;          // 企业出售碳资产的数量 
        uint256 assetAmount;            // 企业出售碳资产的价钱
        uint256 time;                   // 企业出售碳资产的时间
        uint8   status;                 // 出售订单的状态
    }
    
    uint256 public EnterpriseID = 1;
    uint256 public QualificationID = 1;
    uint256 public RegulatorID = 1;
    uint256 public TransactionID = 1;
    uint256 public EmissionResourceID = 1;
    uint256 public EnterpriseAssetID = 1;
    
    uint256 public SIGNIN_CREDIT = uint256(50);
    uint256 public TOTAL_EMISSION = uint256(1000);  // 默认固定下发的额度
    
    uint256[] public EnterpriseIDList;
    uint256[] public RegulatorIDList;
    uint256[] public QualificationIDList;
    uint256[] public TransactionIDList;
    uint256[] public EmissionResourceIDList;
    uint256[] public EnterpriseAssetIDList;
     
    mapping(uint256 => address) public userIdQueryAddress; // 用户ID映射用户地址
    
    mapping(address => Enterprise) public EnterprisesMap;  // 企业ID映射企业信息

    mapping(uint256 => Qualification) public QualificationsMap;  // 资质ID映射资质信息
    
    mapping(address => Regulator) public RegulatorsMap;  // 监管机构ID映射监管机构信息

    mapping(uint256 => Transaction) public TransactionsMap;  // 交易ID映射交易信息
    
    mapping(uint256 => EmissionResource) public EmissionResourcesMap;  // 排放资源ID映射排放资源信息
    
    mapping(uint256 => EnterpriseAsset) public EnterpriseAssetsMap;   // 企业出售额度ID映射企业出售详细订单
    
    // 上传审核的事件
    event UploadQualification(address indexed _acount,string indexed _name,string indexed _content);
    // 审批企业申请的事件
    event VerifyQualification(address indexed _enterpriseAddr,uint256 indexed _emissionLimit);
    // 交易碳额度
    event TransferEmissionLimit(address indexed _from,address indexed _to,uint256 indexed _amount);
    // 出售碳额度
    event SellEmissionLimit(uint256 indexed _emissionLimitCount,uint256 indexed _amount);
    // 更新企业账户的余额
    event UpdateBalnce(address indexed _enterpriseAddr,uint256 indexed _amount);
    // 更新企业账户的碳排放额度
    event UpdateEmissionLimit(address indexed _enterpriseAddr,uint256 indexed _emissionLimit);
    // 申请企业碳排放
    event UploadEnterpriseEmission(address indexed _enterpriseAddr,uint256 indexed _emissionEmission);
    // 监管机构审核企业碳排放
    event VerifyEnterpriseEmission(address indexed _form,address indexed _to,bool indexed _isAppore);
    // 企业更新总需排放量
    event UpdateEnterpriseEmission(address indexed _enterpriseAddr,uint256 indexed _totalEmissions); 
    // 企业碳排放
    event EnterpriseEmission(address indexed _enterpriseAddr,uint256 indexed _emissionEmission);
}