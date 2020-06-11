

package com.example.project;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Parcel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.MultiFactor;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.UserInfo;

import java.util.List;

@SuppressLint("ParcelCreator")
public class User extends FirebaseUser {
    public String username;
    public String TelNumber;
    public String homeAddress;
    public String emailAddress;
    public String password;
    public String type;
    public String friend_request_from;
    public int height;
    public int weight;
    public int age;
    public User(String Un, String TN, String HAD, String ED, String pw, String t, String f, int h, int w, int a){
        username=Un;
        TelNumber=TN;
        homeAddress=HAD;
        emailAddress=ED;
        password=pw;
        type=t;
        friend_request_from=f;
        height=h;
        weight=w;
        age=a;
    }

    @NonNull
    @Override
    public String getUid() {
        return null;
    }

    @NonNull
    @Override
    public String getProviderId() {
        return null;
    }

    @Override
    public boolean isAnonymous() {
        return false;
    }

    @Nullable
    @Override
    public List<String> zza() {
        return null;
    }

    @NonNull
    @Override
    public List<? extends UserInfo> getProviderData() {
        return null;
    }

    @NonNull
    @Override
    public FirebaseUser zza(@NonNull List<? extends UserInfo> list) {
        return null;
    }

    @Override
    public FirebaseUser zzb() {
        return null;
    }

    @NonNull
    @Override
    public FirebaseApp zzc() {
        return null;
    }

    @Nullable
    @Override
    public String getDisplayName() {
        return null;
    }

    @Nullable
    @Override
    public Uri getPhotoUrl() {
        return null;
    }

    @Nullable
    @Override
    public String getEmail() {
        return null;
    }

    @Nullable
    @Override
    public String getPhoneNumber() {
        return null;
    }

    @Override
    public boolean isEmailVerified() {
        return false;
    }

    @Nullable
    @Override
    public String zzd() {
        return null;
    }

    @NonNull
    @Override
    public zzff zze() {
        return null;
    }

    @Override
    public void zza(@NonNull zzff zzff) {

    }

    @NonNull
    @Override
    public String zzf() {
        return null;
    }

    @NonNull
    @Override
    public String zzg() {
        return null;
    }

    @Nullable
    @Override
    public FirebaseUserMetadata getMetadata() {
        return null;
    }

    @NonNull
    @Override
    public MultiFactor getMultiFactor() {
        return null;
    }

    @Override
    public void zzb(List<MultiFactorInfo> list) {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}