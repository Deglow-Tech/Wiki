package com.example.ejemplonavegacion;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ejemplonavegacion.DataBase.ContactDAO;
import com.example.ejemplonavegacion.DataBase.DataBaseHelper;
import com.example.ejemplonavegacion.Entities.Contact;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView text;
    private View view;
    private Button btn_cambiar, btn_mostrar;
    private EditText txt_name, txt_phone;
    private Spinner spn_priority;

    public ContactFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactFragment newInstance(String param1, String param2) {
        ContactFragment fragment = new ContactFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_contact, container, false);
        text = view.findViewById(R.id.textView4);
        btn_cambiar = view.findViewById(R.id.btn_action);
        btn_mostrar = view.findViewById(R.id.btn_show);
        txt_phone = view.findViewById(R.id.txt_phone);
        txt_name = view.findViewById(R.id.txt_name);
        spn_priority = view.findViewById(R.id.spr_priority);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(inflater.getContext(), R.array.priority, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_priority.setAdapter(adapter);

        btn_cambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //text.setText(txt_phone.getText().toString().concat(txt_name.getText().toString()));
                text.setText(spn_priority.getSelectedItem().toString());
                Contact contact = new Contact(txt_name.getText().toString(), txt_phone.getText().toString(), spn_priority.getSelectedItem().toString());
                DataBaseHelper dataBaseHelper = new DataBaseHelper(container.getContext());
                ContactDAO.addContact(dataBaseHelper, contact);
            }
        });

        btn_mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(container.getContext());
                List<Contact> contactList = ContactDAO.getContacts(dataBaseHelper);
                Toast.makeText(container.getContext(), contactList.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        txt_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = txt_phone.getText().toString();
                if(texto.equals("Phone"))
                    txt_phone.setText("");
            }
        });

        txt_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = txt_name.getText().toString();
                if(texto.equals("Name"))
                    txt_name.setText("");
            }
        });

        return view;
    }
}