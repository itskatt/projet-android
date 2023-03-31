package fr.equipeR.teltechmobile.model;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe <strong>singleton</strong> qui contient tout les smartphones.
 */
public class SmartphoneList {
    private final List<Smartphone> smartphones = new ArrayList<>();
    private final RequestQueue queue;
    private final String TAG = getClass().getName();

    private static SmartphoneList instance;

    private SmartphoneList(Context context) {
        queue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public int size() {
        return smartphones.size();
    }

    /**
     * @see List#get(int)
     */
    public Smartphone get(int index) {
        return smartphones.get(index);
    }

    /**
     * Renvoie un smartphone en fonction de son id.
     *
     * @param id L'id du smartphone à récupérer.
     * @return Le smartphone correspondant à l'id, ou null si aucun smartphone avec
     * @see Smartphone#getId()
     */
    public Smartphone getById(int id) {
        // remplacer par un meilleur algo si les performances deviennent un problème
        for (Smartphone smartphone : smartphones) {
            if (smartphone.getId() == id) {
                return smartphone;
            }
        }

        return null;
    }

    public boolean isEmpty() {
        return smartphones.isEmpty();
    }

    public void fetchSmartphones() {
        final String url = "https://tel.extracursus.live/api/public/articles.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                url, response -> {
                    Log.i(TAG, "fetchSmartphones: les smartphones ont été recuperés");
            // TODO: 31/03/2023 parse le json
                },
                error -> Log.e(TAG, "fetchSmartphones: erreur lors de la récuperation des smartphones", error)
        );

        Log.d(TAG, "fetchSmartphones: récuperation des smartphones...");
        queue.add(jsonObjectRequest);
    }

    @NonNull
    public static SmartphoneList getInstance(Context context) {
        if (instance == null) {
            instance = new SmartphoneList(context);
        }

        return instance;
    }

    @NonNull
    public static SmartphoneList getInstance() throws IllegalStateException {
        if (instance == null) {
            throw new IllegalStateException("Cette classe n'a pas été correctement initialisée, " +
                    "utilisez getInstance(Context) pour le premier appel.");
        }

        return instance;
    }
}
