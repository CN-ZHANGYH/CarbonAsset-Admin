pragma solidity ^0.6.10;
pragma experimental ABIEncoderV2;

// 数字化纪念卡
contract SouvenirCard {
    
    // 纪念卡结构体
    struct CardInfo{
        uint cardId;        // 纪念卡ID
        uint level;         // 纪念卡星级
        string cardName;    // 纪念卡名称
        string cardDesc;    // 纪念卡描述
        string cardUrl;     // 纪念卡的url
        string category;    // 纪念卡分类
        uint   credit;      // 需要积分的数量
    }
    
    
    mapping(string => CardInfo) public CardInfoMap;
    mapping(string => string[]) public UserOfCardListMap;
    
    uint[] public CardInfoList;
    
    
    // 添加一个纪念卡
    function RegisterCard(uint _cardId,uint _level,string memory _cardName,string memory _cardDesc,string memory _cardUrl,string memory _categoty,uint _credit) public {
        require(!IsCardExist(_cardName),"当前的纪念卡已经存在");
       CardInfo storage _cardInfo =  CardInfoMap[_cardName];
       _cardInfo.cardId = _cardId;
       _cardInfo.cardName = _cardName;
       _cardInfo.cardDesc = _cardDesc;
       _cardInfo.cardUrl = _cardUrl;
       _cardInfo.category = _categoty;
       _cardInfo.credit = _credit;
       
       CardInfoList.push(_cardId);
    }
    
    // 判断一下该纪念卡是否已经存在
    function IsCardExist(string memory _cardName) public returns(bool){
       if (CardInfoMap[_cardName].cardId != 0){
           return true;
       }
       return false;
    }
    
    
    // 查询当前的纪念卡是否已经存在
    function QueryCardInfo(string memory _cardName) public returns(CardInfo memory) {
        require(IsCardExist(_cardName),"当前的纪念卡不存在");
        return CardInfoMap[_cardName];
    }
    
    // 企业添加一个纪念卡
    function UserBindCard(string memory _enterpriseName,string memory _cardName) public {
        require(IsCardExist(_cardName),"当前的纪念卡不存在");
        UserOfCardListMap[_enterpriseName].push(CardInfoMap[_cardName].cardName);
    }
    
    // 查询企业的所有纪念卡
    function QueryEnterpriseCardList(string memory _enterpriseName) public returns(CardInfo[] memory){
        string[] memory cardList = UserOfCardListMap[_enterpriseName];
        CardInfo[] memory cardInfoList = new CardInfo[](cardList.length);
        for(uint i = 0; i < cardList.length; i++){
            cardInfoList[i] = CardInfoMap[cardList[i]];
        }
        return cardInfoList;
    }
    
    // 查询该企业是否已经有纪念卡
    function QueryEnterpriseIsHasCard(string memory _enterpriseName,string memory _cardName) public returns(bool){
        string[] memory cardList = UserOfCardListMap[_enterpriseName];
        for (uint i = 0;i < cardList.length; i++){
            if (keccak256(bytes(_cardName)) == keccak256(bytes(cardList[i]))){
                return true;
            }
            return false;
        }
    }
    
}