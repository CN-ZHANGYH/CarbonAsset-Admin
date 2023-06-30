package com.ruoyi.carbon.model.bo;

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
public class CarbonUserServiceUploadQualificationInputBO {
  private String _enterpriseAddress;

  private String _qualificationName;

  private String _qualificationContent;

  private String _qualificationLeader;

  private String _qualificationIndustry;

  private String _qualificationUserName;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_enterpriseAddress);
    args.add(_qualificationName);
    args.add(_qualificationContent);
    args.add(_qualificationLeader);
    args.add(_qualificationIndustry);
    args.add(_qualificationUserName);
    return args;
  }
}
