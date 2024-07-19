package com.example.timeofnamaz;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timeofnamaz.CurAdapter.LocationAdapterr;
import com.example.timeofnamaz.Models.Place;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LocationListener {
    boolean isPermissionGranter;
    LocationManager locationManager;
    com.google.android.gms.location.LocationRequest locationRequest;
    RecyclerView recyclerView;
    double d1, d2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        recyclerView = findViewById(R.id.recl);
//        progressBar = findViewById(R.id.progressBar3);


        checkgps();
        checkPermission();
//        progressBar.setVisibility(View.VISIBLE);

        if (isPermissionGranter) {
            LocationUpdates();


        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add("logout");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getTitle().equals("logout")) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void checkgps() {
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(3000);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addAllLocationRequests(Collections.singleton(locationRequest)).setAlwaysShow(true);
        Task<LocationSettingsResponse> locationSettingsResponseTask = LocationServices.getSettingsClient(getApplicationContext()).checkLocationSettings(builder.build());

        locationSettingsResponseTask.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {
                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                } catch (ApiException e) {
                    if (e.getStatusCode() == LocationSettingsStatusCodes.RESOLUTION_REQUIRED) {
                        ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                        try {
                            resolvableApiException.startResolutionForResult(MainActivity.this, 101);
                        } catch (IntentSender.SendIntentException ex) {
                            ex.printStackTrace();
                        }
                    }
                    if (e.getStatusCode() == LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE) {
                        Toast.makeText(MainActivity.this, "setting is unavailable", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    @SuppressLint("MissingPermission")
    private void LocationUpdates() {
        locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, (LocationListener) MainActivity.this);
    }

    private void checkPermission() {

        Dexter.withContext(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                isPermissionGranter = true;
                LocationUpdates();
                sor();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), " ");
                intent.setData(uri);
                startActivity(intent);
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }


        }).check();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "GPS is now enabled", Toast.LENGTH_SHORT).show();

            }
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "GPS permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        Toast.makeText(this, "Location" + location.getLatitude() + ":" + location.getLongitude(), Toast.LENGTH_SHORT).show();
        d1 = location.getLatitude();
        d2 = location.getLongitude();
        sor();
//            progressBar.setVisibility(View. INVISIBLE);
    }

    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {

    }

    @Override
    public void onFlushComplete(int requestCode) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {


    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void sor() {
        double lat = d1;
        double lng = d2;
        LatLng latLng = new LatLng(lat, lng);

        //set up list
        ArrayList<Place> places = new ArrayList<Place>();

        places.add(new Place("Ramzani Masjid", new LatLng(28.56069, 77.29293), 1));
        places.add(new Place("Bilal Masjid", new LatLng(28.55848, 77.29409), 2));
        places.add(new Place("Makki Masjid", new LatLng(28.56003, 77.29423), 3));
        places.add(new Place("Jama Masjid (Okhla)", new LatLng(28.56327, 77.29042), 4));
        places.add(new Place("Khalilullah Masjid", new LatLng(28.56069, 77.29293), 5));
        places.add(new Place("Musa Masjid", new LatLng(28.55957, 77.28785), 6));
        places.add(new Place("Masjid Ahle Hadees", new LatLng(28.55787, 77.29311), 7));
        places.add(new Place("Ishat e Islam Masjid", new LatLng(28.55710, 77.29297), 8));
        places.add(new Place("Char Minar Masjid", new LatLng(28.55518, 77.29537), 9));
        places.add(new Place("Masjid Umar", new LatLng(28.55383, 77.29560), 10));


        //sort the list, give the Comparator the current location
        Collections.sort(places, new SortPlaces(latLng));

        LocationAdapterr adapter = new LocationAdapterr(places, this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);


    }

    public class SortPlaces implements Comparator<Place> {
        LatLng currentLoc;

        public SortPlaces(LatLng current) {
            currentLoc = current;
        }

        @Override
        public int compare(final Place place1, final Place place2) {
            double lat1 = place1.latlng.latitude;
            double lon1 = place1.latlng.longitude;
            double lat2 = place2.latlng.latitude;
            double lon2 = place2.latlng.longitude;

            double distanceToPlace1 = distance(currentLoc.latitude, currentLoc.longitude, lat1, lon1);
            double distanceToPlace2 = distance(currentLoc.latitude, currentLoc.longitude, lat2, lon2);
            return (int) (distanceToPlace1 - distanceToPlace2);
        }

        public double distance(double fromLat, double fromLon, double toLat, double toLon) {
            double radius = 6378137;   // approximate Earth radius, *in meters*
            double deltaLat = toLat - fromLat;
            double deltaLon = toLon - fromLon;
            double angle = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(deltaLat / 2), 2) + Math.cos(fromLat) * Math.cos(toLat) * Math.pow(Math.sin(deltaLon / 2), 2)));
            return radius * angle;
        }
    }


}