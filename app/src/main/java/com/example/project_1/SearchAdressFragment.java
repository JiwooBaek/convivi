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

import model.addressSearch.Address;
import model.addressSearch.AddressSearch;
import model.addressSearch.Document;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchAdressFragment extends Fragment {

    private ArrayList<AddressItem> addressItems;
    private AddressAdapter addressAdapter;
    private RecyclerView addressRecyclerView;
    private LinearLayoutManager layoutManager;
    private EditText addressSearch;
    ArrayList<Document> items = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (ViewGroup) inflater.inflate(R.layout.fragment_search_adress, container, false);

        addressRecyclerView = (RecyclerView) view.findViewById(R.id.addressRecyclerView);
        addressSearch = (EditText) view.findViewById(R.id.addressSearch);
        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), RecyclerView.VERTICAL, false);
        addressRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL));
        addressRecyclerView.setLayoutManager(layoutManager);
        addressAdapter = new AddressAdapter(getActivity().getApplicationContext(), items, addressSearch, addressRecyclerView);
        addressRecyclerView.setAdapter(addressAdapter);

        addressSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                addressRecyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count >= 2) {
                    items.clear();
                    addressAdapter.claer();
                    addressAdapter.notifyDataSetChanged();
                    ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
                    Call<AddressSearch> call = apiInterface.getSearchAddress(getString(R.string.restapi_key), s.toString());
                    call.enqueue(new Callback<AddressSearch>() {
                        @Override
                        public void onResponse(Call<AddressSearch> call, Response<AddressSearch> response) {
                            if (response.isSuccessful()) {
                                assert response.body() != null;
                                for (Document document : response.body().getDocuments()) {
                                    addressAdapter.addItem(document);
                                    Log.d("응답", addressAdapter.getItemCount() + "");
                                }
                                addressAdapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onFailure(Call<AddressSearch> call, Throwable throwable) {

                        }
                    });
                } else {
                    if (s.length() <= 0) {
                        addressRecyclerView.setVisibility(View.GONE);
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        return view;
    }

}
