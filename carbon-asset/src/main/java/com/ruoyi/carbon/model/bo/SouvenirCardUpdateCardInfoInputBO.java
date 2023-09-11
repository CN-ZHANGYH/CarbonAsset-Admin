package com.ruoyi.carbon.model.bo;

import java.lang.Object;
import java.lang.String;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SouvenirCardUpdateCardInfoInputBO {
  private BigInteger _cardId;

  private BigInteger _level;

  private String _cardName;

  private String _cardDesc;

  private String _cardUrl;

  private String _categoty;

  private BigInteger _credit;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_cardId);
    args.add(_level);
    args.add(_cardName);
    args.add(_cardDesc);
    args.add(_cardUrl);
    args.add(_categoty);
    args.add(_credit);
    return args;
  }
}
