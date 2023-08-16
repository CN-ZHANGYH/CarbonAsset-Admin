package com.ruoyi.carbon.model.bo;

import java.lang.Object;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarbonAssetServiceSubEnterpriseCreditInputBO {
  private BigInteger _credit;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_credit);
    return args;
  }
}
