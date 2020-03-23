package com.henu.reservoir.util;

import ucar.nc2.Attribute;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;

import java.io.IOException;

public class Sentinel3_A {
    public static void Altitude_Sentinel3_A(String str_in, String out01, String out20) throws IOException {
        NetcdfFile ncFile = NetcdfFile.open("D:\\Documents\\JavaWeb\\reservoir-data\\radar\\S3A_SR_2_LAN____20180429T133721_20180429T142749_20180525T010452_3028_030_309______LN3_O_NT_003.SEN3\\enhanced_measurement.nc");
        //获得cycle_number和pass_number属性
        Attribute cycle_number = ncFile.findGlobalAttribute("cycle_number");
        Attribute pass_number = ncFile.findGlobalAttribute("pass_number");
        //获得指定变量
        // 20Hz
        Variable time_20 = ncFile.findVariable("time_20_ku"); // 时间 time Envisat:time_20 Cryosat:time_20_ku
        Variable lat_20 = ncFile.findVariable("lat_20_ku"); // 纬度 lat Envisat:lat_20 Cryosat:lon_20_ku
//        lon_20          = ncread(fname_in,'lon_20_ku');  % 经度 lon Envisat:lon_20 Cryosat:lat_20_ku
//        %1Hz
//        time_01      = ncread(fname_in,'time_01');
//        lat_01       = ncread(fname_in,'lat_01');
//        lon_01       = ncread(fname_in,'lon_01');
//
//        alt          = ncread(fname_in,'alt_20_ku');  % 轨道高 alt Envisat:alt_20 Cryosat:alt_20_ku
//        range        = ncread(fname_in, 'range_ocog_20_ku');% range_ku Envisat:range_ice1_20_ku Cryosat:range_ocog_20_ku;Sentinel:range_ice_20_ku、range_ice_sheet_20_ku
//        %地理矫正参量1hz
//        model_dry    = ncread(fname_in,'mod_dry_tropo_cor_meas_altitude_01');%干对流层修正系数
//        model_wet    = ncread(fname_in,'mod_wet_tropo_cor_meas_altitude_01');%湿对流层修正系数
//        iono_corr    = ncread(fname_in,'iono_cor_alt_20_ku');%电离层修正系数
//        %%地理矫正参量1hz
//        solid_earth  = ncread(fname_in,'solid_earth_tide_01');%固体潮修正系数
//        pole_tide    = ncread(fname_in,'pole_tide_01');%极潮修正系数
//        geoid = ncread(fname_in,'geoid_01');%基准面差

        System.out.println("cycle_number: " + cycle_number.getNumericValue());
        System.out.println("pass_number: " + pass_number.getNumericValue());
        System.out.println("time_20" + time_20.toString());
        System.out.println("lat_20" + lat_20.toString());
    }
}
