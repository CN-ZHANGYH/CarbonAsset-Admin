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
public class CarbonAssetServiceBuyEmissionLimitInputBO {
  private String _enterpriseSeller;

  private BigInteger _eassetId;

  private BigInteger _quantity;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_enterpriseSeller);
    args.add(_eassetId);
    args.add(_quantity);
    return args;
  }
}
