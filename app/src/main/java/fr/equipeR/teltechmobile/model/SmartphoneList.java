package fr.equipeR.teltechmobile.model;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import fr.equipeR.teltechmobile.R;

/**
 * Classe <strong>singleton</strong> qui contient tout les smartphones.
 */
public class SmartphoneList {
    private final List<Smartphone> smartphones = new ArrayList<>();
    private final RequestQueue queue;
    private final String TAG = getClass().getName();
    private final Context applicationContext;

    private static SmartphoneList instance;

    private SmartphoneList(Context context) {
        applicationContext = context.getApplicationContext();
        queue = Volley.newRequestQueue(applicationContext);
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

    /**
     * Récupère la liste des smartphones disponibles de façon asyncrone.
     *
     * @param onSmartphonesFetchedListener Une fonction qui est appelée une fois que les smartphones
     *                                     ont été recuperé.
     * @param onErrorListener              Une fonction qui est appelée si une erreur survient.
     */
    public void fetchSmartphones(@NonNull Consumer<List<Smartphone>> onSmartphonesFetchedListener,
                                 @NonNull Consumer<VolleyError> onErrorListener) {
        final String url = applicationContext.getString(R.string.smartphones_api_endpoint);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,
                response -> {
                    Log.d(TAG, "fetchSmartphones: les smartphones ont été recuperés");
                    try {
                        JSONArray smartphonesJson = response.getJSONArray("articles");
                        for (int i = 0; i < smartphonesJson.length(); i++) {
                            JSONObject smartphoneJson = smartphonesJson.getJSONObject(i);
                            smartphones.add(
                                    new SmartphoneBuilder()
                                            .setId(smartphoneJson.getInt("article_id"))
                                            .setName(smartphoneJson.getString("article_name"))
                                            .setDescription(smartphoneJson.getString("description"))
                                            .setRating(smartphoneJson.getInt("rating"))
                                            .setYear(smartphoneJson.getInt("year"))
                                            .setPriceTax(Double.parseDouble(smartphoneJson.getString("price_tax")))
                                            .setImageID(smartphoneJson.getString("image"))
                                            .setSupplierName(smartphoneJson.getString("supplier_name"))
                                            .setQuantity(smartphoneJson.getInt("quantity"))
                                            .createSmartphone()
                            );
                        }
                    } catch (JSONException e) {
                        Log.e(TAG, "fetchSmartphones: erreur lors de la conversion du JSON", e);
                    }

                    Log.i(TAG, "fetchSmartphones: nombre de smartphones récup : " + smartphones.size());
                    onSmartphonesFetchedListener.accept(smartphones);
                },
                error -> {
                    Log.e(TAG, "fetchSmartphones: erreur lors de la récuperation des smartphones", error);
                    onErrorListener.accept(error);
                }
        );

        Log.d(TAG, "fetchSmartphones: récuperation des smartphones...");
        queue.add(jsonObjectRequest);
    }

    public static void initialise(Context context) {
        if (instance == null) {
            instance = new SmartphoneList(context);
        }
    }

    @NonNull
    public static SmartphoneList getInstance() throws IllegalStateException {
        if (instance == null) {
            throw new IllegalStateException("Cette classe n'a pas été correctement initialisée, " +
                    "utilisez initialise(Context) avant le premier appel.");
        }

        return instance;
    }
}
