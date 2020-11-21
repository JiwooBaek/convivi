package com.example.project_1;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_1.Adapter.AddressAdapter;
import com.example.project_1.api.ApiClient;
import com.example.project_1.api.ApiInterface;

import java.util.ArrayList;

import model.addressSearch.AddressSearch;
import model.addressSearch.Document;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchAdressFragment extends Fragment {

    private ArrayList<AddressItem> addressItems;
    private ArrayList<Document> documents;
    private AddressAdapter addressAdapter;
    private RecyclerView addressRecyclerView;
    private LinearLayoutManager layoutManager;
    private EditText addressSearch;
    private AddressItem addressItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (ViewGroup)inflater.inflate(R.layout.fragment_search_adress, container, false);

        addressRecyclerView = (RecyclerView)view.findViewById(R.id.addressRecyclerView);
        addressSearch = (EditText) view.findViewById(R.id.addressSearch);
        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),RecyclerView.VERTICAL, false);
        addressRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL));
        addressRecyclerView.setLayoutManager(layoutManager);
        addressAdapter = new AddressAdapter(documents);
        addressRecyclerView.setAdapter(addressAdapter);

        addressSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                addressRecyclerView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 1){
                    addressAdapter.clear();
                    addressAdapter.notifyDataSetChanged();
                    ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
                    Call<AddressSearch> call = apiInterface.getSearchAddress(getString(R.string.restapi_key), s.toString());
                    call.enqueue(new Callback<AddressSearch>() {
                        @Override
                        public void onResponse(Call<AddressSearch> call, Response<AddressSearch> response) {
                            Log.d("응답", response.body().toString());
                            if(response.isSuccessful()){
                                for (Document document : response.body().getDocuments()) {
                                    documents.add(document);
                                } addressAdapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onFailure(Call<AddressSearch> call, Throwable throwable) {

                        }
                    });

                } else {
                    addressRecyclerView.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }
}
