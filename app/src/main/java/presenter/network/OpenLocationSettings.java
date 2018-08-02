package presenter.network;

import android.content.Context;
import android.location.LocationManager;


/**
 * Created by dinamounib on 7/27/16.
 */
public class OpenLocationSettings {

    LocationManager lm;
    boolean gps_enabled = false;
    boolean network_enabled = false;
    Context con;


    public boolean openSettings(Context context) {
        con = context;
        boolean result;
        lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }

        if (!gps_enabled && !network_enabled) {
            result = false;
        } else {
            result = true;

        }
        return result;
    }


}
