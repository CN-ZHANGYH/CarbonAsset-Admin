package com.ruoyi.carbon.model.bo;

import java.lang.Boolean;
import java.lang.Object;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarbonUserServiceVerifyQualificationInputBO {
  private String _regulatorAddress;

  private String _enterpriseAddress;

  private Boolean _isApprove;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_regulatorAddress);
    args.add(_enterpriseAddress);
    args.add(_isApprove);
    return args;
  }
}
