/********************************************************************************
# * Licensed Materials - Property of IBM
# * (C) Copyright IBM Corporation 2019. All Rights Reserved
# *
# * This program and the accompanying materials are made available under the
# * terms of the Eclipse Public License 2.0 which is available at
# * http://www.eclipse.org/legal/epl-2.0.
# *
# * US Government Users Restricted Rights - Use, duplication or
# * disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
# *
# ********************************************************************************/

package com.example.demo.rest.externalsource.transport;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

/**
 * A POJO (Plain Old Java Object) for response objects from Socrata. These represent the fields in the NYPD database
 */
@ToString
public class SocrataResponseData {
    //Complaint
    @JsonProperty("cmplnt_num") public int complaintNum;
    @JsonProperty("cmplnt_fr_dt") public LocalDate complaintStartDate;
    @JsonProperty("cmplnt_to_dt") public LocalDate complaintEndDate;
    @JsonProperty("cmplnt_fr_tm") public LocalTime complaintStartTime;
    @JsonProperty("cmplnt_to_tm") public LocalTime complaintEndTime;
    @JsonProperty("crm_atpt_cptd_cd") public String crimeStatus;
    @JsonProperty("jutisdiction_code") public int jurisdictionCode;
    @JsonProperty("juris_desc") public String jurisdictionDesc;
    @JsonProperty("ky_cd") public int offenceClass;
    @JsonProperty("law_cat_cd") public String offenceLevel;
    @JsonProperty("ofns_desc") public String offenceDesc;
    @JsonProperty("pd_cd") public String internalClassCode;
    @JsonProperty("pd_desc") public String classDesc;
    @JsonProperty("rpt_dt") public LocalDate dateReported;
    @JsonProperty("loc_of_occur_desc") public String occurrenceLocation;
    @JsonProperty("latitude") public float latitude;
    @JsonProperty("longitude") public float longitude;

    //Location
    @JsonProperty("addr_pct_cd") public int precinctCode;
    @JsonProperty("boro_nm") public String boroName;
    @JsonProperty("hadevelopt") public String houseDev;
    @JsonProperty("parks_nm") public String parkName;
    @JsonProperty("patrol_boro") public String patrolBoro;
    @JsonProperty("prem_typ_desc") public String premisesDesc;
    @JsonProperty("station_name") public String stationName;
    @JsonProperty("transit_district") public String transitDistrict;

    //Person (Suspect)
    @JsonProperty("susp_age_group") public String suspAge;
    @JsonProperty("susp_race") public String suspRace;
    @JsonProperty("susp_sex") public String suspSex;

    //Person (Victim)
    @JsonProperty("vic_age_group") public String vicAge;
    @JsonProperty("vic_race") public String vicRace;
    @JsonProperty("vic_sex") public String vicSex;
}
