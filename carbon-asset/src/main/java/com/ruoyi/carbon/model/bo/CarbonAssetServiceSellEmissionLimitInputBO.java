package com.ruoyi.carbon.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarbonAssetServiceSellEmissionLimitInputBO {
  private BigInteger _emissionLimitCount;

  private BigInteger _amount;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_emissionLimitCount);
    args.add(_amount);
    return args;
  }
}
