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
public class SouvenirCardUserBindCardInputBO {
  private String _enterpriseName;

  private BigInteger _cardId;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_enterpriseName);
    args.add(_cardId);
    return args;
  }
}
