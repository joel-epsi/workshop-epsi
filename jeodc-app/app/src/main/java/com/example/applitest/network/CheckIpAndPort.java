package com.example.applitest.network;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.telephony.mbms.StreamingServiceInfo;

import com.example.applitest.common.ListenerManager;

import org.apache.commons.net.util.SubnetUtils;

import java.util.HashMap;

public class CheckIpAndPort extends ListenerManager<CheckIpAndPortInterface.NotificationListener>
        implements CheckIpAndPortInterface {


    CheckIpAndPortInterface.StateChecking stateChecking = CheckIpAndPortInterface.StateChecking.INIT;


    HashMap<String, HashMap<String, String>> hashMapDatas = new HashMap<>();


    private DhcpInfo dhcpInfo;
    private WifiManager wifiManager;


    public CheckIpAndPort() {}


    public void startChecking(Context context){

        wifiManager= (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        dhcpInfo = wifiManager.getDhcpInfo();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //TODO Methods to check
                stateChecking = StateChecking.CHECKING;
                notifyListeners();

                if(dhcpInfo.netmask==0){
                    System.out.println(intToIp(dhcpInfo.netmask));

                    stateChecking = StateChecking.ERROR;
                    notifyListeners();
                }

                SubnetUtils subnetUtils = new SubnetUtils("10.60.48.0/22");

                String[] ips = subnetUtils.getInfo().getAllAddresses();

                for (String s : ips) {

                    System.out.println(s);
                }
            }
        });

        thread.start();
    }

    private synchronized void notifyListeners() {
        for (NotificationListener listener : listeners) {
            listener.onStateCheckingChanged();
        }

    }

    public String intToIp(int i) {

        return ((i >> 24 ) & 0xFF ) + "." +
                ((i >> 16 ) & 0xFF) + "." +
                ((i >> 8 ) & 0xFF) + "." +
                ( i & 0xFF) ;
    }

    @Override
    public StateChecking getState() {
        return stateChecking;
    }
}
