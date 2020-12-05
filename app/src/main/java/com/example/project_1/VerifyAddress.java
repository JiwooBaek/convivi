package com.example.project_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_1.api.ApiClient;
import com.example.project_1.api.ApiInterface;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import net.daum.android.map.MapViewEventListener;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import model.UserModel;
import model.regioncode.Document;
import model.regioncode.Regioncode;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyAddress extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    DatabaseReference user = FirebaseDatabase.getInstance().getReference().child("Users");

   Button verificationButton;
   TextView text;
   String userAddress;
   String x;
   String y;
   List<Document> region = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify__address);

        verificationButton = (Button) findViewById(R.id.verificationButton);
        text = (TextView) findViewById(R.id.text);
        user.child(firebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserModel currentUser = dataSnapshot.getValue(UserModel.class);
                userAddress = currentUser.address;
                Log.d("응답", "db" + userAddress);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        MapView mapView = new MapView(this);
        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeadingWithoutMapMoving);
        mapView.setShowCurrentLocationMarker(true);
        mapView.setCurrentLocationEventListener(new MapView.CurrentLocationEventListener() {
            @Override
            public void onCurrentLocationUpdate(MapView mapView, MapPoint mapPoint, float v) {
                mapView.setMapCenterPointAndZoomLevel(mapPoint, 1, true);
                MapPOIItem marker = new MapPOIItem();
                marker.setMapPoint(mapPoint);
                marker.setMarkerType(MapPOIItem.MarkerType.BluePin);
                mapViewContainer.addView(mapView);

                x = String.valueOf(mapPoint.getMapPointGeoCoord().longitude);
                y= String.valueOf(mapPoint.getMapPointGeoCoord().latitude);

                ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
                Call<Regioncode> call = apiInterface.getRegioncode(getString(R.string.restapi_key), x, y);
                call.enqueue(new Callback<Regioncode>() {
                    @Override
                    public void onResponse(Call<Regioncode> call, Response<Regioncode> response) {
                        region.addAll(response.body().getDocuments());
                        if(region.get(0).getRegion3depthName().equals(userAddress) || region.get(1).getRegion3depthName().equals(userAddress)){
                            text.setText("현 위치가 설정하신 동네와 일치합니다.");
                            verificationButton.setEnabled(true);
                            verificationButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    user.child(firebaseAuth.getInstance().getCurrentUser().getUid()).child("addressFlag").setValue(true);
                                    Toast.makeText(getApplication(),"인증되었습니다", Toast.LENGTH_LONG).show();
                                }
                            });
                        } else {
                            text.setText("현 위치가 설정하신 동네와 일치하지 않습니다.");
                            verificationButton.setEnabled(false);
                        }
                        Log.d("응답", "0" + region.get(0).getRegion3depthName());
                        Log.d("응답", "1" + region.get(1).getRegion3depthName());
                    }

                    @Override
                    public void onFailure(Call<Regioncode> call, Throwable throwable) {

                    }
                });
            }

            @Override
            public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {

            }

            @Override
            public void onCurrentLocationUpdateFailed(MapView mapView) {

            }

            @Override
            public void onCurrentLocationUpdateCancelled(MapView mapView) {

            }
        });

    }
}