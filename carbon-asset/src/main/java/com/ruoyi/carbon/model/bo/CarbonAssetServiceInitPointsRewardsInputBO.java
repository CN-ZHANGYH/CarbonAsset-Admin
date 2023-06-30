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
public class CarbonAssetServiceInitPointsRewardsInputBO {
  private List<String> _enterpriseAddressList;

  private BigInteger _credited;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_enterpriseAddressList);
    args.add(_credited);
    return args;
  }
}
