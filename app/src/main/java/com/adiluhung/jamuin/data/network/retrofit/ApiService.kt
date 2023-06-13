package com.adiluhung.jamuin.data.network.retrofit

import com.adiluhung.jamuin.data.network.responses.AddCartResponse
import com.adiluhung.jamuin.data.network.responses.AddFavoriteResponse
import com.adiluhung.jamuin.data.network.responses.AddJamuResponse
import com.adiluhung.jamuin.data.network.responses.CartResponse
import com.adiluhung.jamuin.data.network.responses.CheckoutResponse
import com.adiluhung.jamuin.data.network.responses.DeleteCartResponse
import com.adiluhung.jamuin.data.network.responses.DetailIngredientResponse
import com.adiluhung.jamuin.data.network.responses.DetailJamuResponse
import com.adiluhung.jamuin.data.network.responses.DetailProductResponse
import com.adiluhung.jamuin.data.network.responses.DetailStoreResponse
import com.adiluhung.jamuin.data.network.responses.FavoriteResponse
import com.adiluhung.jamuin.data.network.responses.IngredientResponse
import com.adiluhung.jamuin.data.network.responses.JamuResponse
import com.adiluhung.jamuin.data.network.responses.LoginResponse
import com.adiluhung.jamuin.data.network.responses.LogoutResponse
import com.adiluhung.jamuin.data.network.responses.ProductResponse
import com.adiluhung.jamuin.data.network.responses.ProductsItem
import com.adiluhung.jamuin.data.network.responses.RegisterResponse
import com.adiluhung.jamuin.data.network.responses.StoreResponse
import com.adiluhung.jamuin.data.network.responses.UpdateJamuResponse
import com.adiluhung.jamuin.data.network.responses.UserResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    object EndPoints {
        // AUTH
        const val POST_REGISTER = "register"
        const val POST_LOGIN = "login"
        const val POST_LOGOUT = "logout"

        // USER
        const val GET_USER_BY_TOKEN = "user"
        const val UPDATE_USER = "user/{id}"
        const val DELETE_USER = "users/{id}"
        const val GET_USER_FAVORITE = "user/favorites"
        const val ADD_USER_FAVORITE = "jamu/{id}/favorite"
        const val DELETE_USER_FAVORITE = "jamu/{id}/favorite"

        // JAMU
        const val GET_JAMU = "jamu"
        const val GET_DETAIL_JAMU = "jamu/{id}"
        const val POST_JAMU = "jamu"
        const val UPDATE_JAMU = "jamu/{id}"
        const val DELETE_JAMU = "jamu/{id}"

        // INGREDIENTS
        const val GET_INGREDIENTS = "ingredients"
        const val GET_DETAIL_INGREDIENTS = "ingredients/{id}"
        const val POST_INGREDIENTS = "ingredients"
        const val UPDATE_INGREDIENTS = "ingredients/{id}"
        const val DELETE_INGREDIENTS = "ingredients/{id}"

        // STORE
        const val GET_STORE = "store"
        const val GET_DETAIL_STORE = "store/{id}"
        const val POST_STORE = "store"
        const val UPDATE_STORE = "store/{id}"
        const val DELETE_STORE = "store/{id}"

        // PRODUCT
        const val GET_PRODUCT = "products"
        const val GET_DETAIL_PRODUCT = "products/{id}"

        // CART
        const val GET_USER_CART = "user/carts"
        const val ADD_USER_CART = "carts"
        const val DELETE_USER_CART = "carts/{id}"

        // TRANSACTION
        const val CONFIRM_CHECKOUT = "confirm-checkout"
    }

    /**
     * AUTHENTICATION
     * */
    @FormUrlEncoded
    @POST(EndPoints.POST_REGISTER)
    @Headers("Accept: application/json")
    fun register(
        @Field("full_name") fullname: String,
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("phone_number") phoneNumber: String,
        @Field("password") password: String,
        @Field("password_confirmation") passwordConfirmation: String,
        @Field("address") address: String,
        @Field("role") role: String,
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST(EndPoints.POST_LOGIN)
    @Headers("Accept: application/json")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Call<LoginResponse>

    @POST(EndPoints.POST_LOGOUT)
    @Headers("Accept: application/json")
    fun logout(
        @Header("Authorization") token: String
    ): Call<LogoutResponse>

    /**
     * USER
     * */
    @GET(EndPoints.GET_USER_BY_TOKEN)
    @Headers("Accept: application/json")
    fun getUserByToken(
        @Header("Authorization") token: String,
    ): Call<UserResponse>

    @POST(EndPoints.UPDATE_USER)
    @FormUrlEncoded
    @Headers("Accept: application/json")
    fun updateUser(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Field("full_name") fullname: String,
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("phone_number") phoneNumber: String,
        @Field("address") address: String,
        @Field("image") image: String?,
    ): Call<UserResponse>

    @GET(EndPoints.GET_USER_FAVORITE)
    @Headers("Accept: application/json")
    fun getUserFavorite(
        @Header("Authorization") token: String,
    ): Call<FavoriteResponse>

    @POST(EndPoints.ADD_USER_FAVORITE)
    @Headers("Accept: application/json")
    fun addUserFavorite(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
    ): Call<AddFavoriteResponse>

    @DELETE(EndPoints.DELETE_USER_FAVORITE)
    @Headers("Accept: application/json")
    fun deleteUserFavorite(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
    ): Call<AddFavoriteResponse>

    /**
     * JAMU
     * */
    @GET(EndPoints.GET_JAMU)
    @Headers("Accept: application/json")
    fun getAllJamu(): Call<JamuResponse>

    @GET(EndPoints.GET_JAMU)
    @Headers("Accept: application/json")
    fun getJamuByQuery(
        @Query("ingredient") ingredient: Int
    ): Call<JamuResponse>

    @GET(EndPoints.GET_DETAIL_JAMU)
    @Headers("Accept: application/json")
    fun getDetailJamu(
        @Path("id") id: Int
    ): Call<DetailJamuResponse>

    @POST(EndPoints.POST_JAMU)
    @Headers("Accept: application/json")
    fun addJamu(
        @Header("Authorization") token: String,
        @Field("category_id") categoryId: Int,
        @Field("main_ingredient_id") mainIngredientId: Int,
        @Field("name") name: String,
        @Field("description") description: String,
        @Field("ingredients") ingredients: String,
        @Field("steps") steps: String,
        @Field("source") source: String,
        @Field("image") image: String,
    ): Call<AddJamuResponse>

    @POST(EndPoints.UPDATE_JAMU)
    @Headers("Accept: application/json")
    fun updateJamu(
        @Header("Authorization") token: String,
        @Query("id") id: Int,
        @Field("category_id") categoryId: Int,
        @Field("name") name: String,
        @Field("description") description: String,
        @Field("ingredients") ingredients: String,
        @Field("steps") steps: String,
        @Field("source") source: String,
        @Field("image") image: String,
    ): Call<UpdateJamuResponse>

    @POST(EndPoints.DELETE_JAMU)
    @Headers("Accept: application/json")
    fun deleteJamu(
        @Header("Authorization") token: String,
        @Query("id") id: Int
    ): Call<UpdateJamuResponse>

    /**
     * INGREDIENTS
     * */
    @GET(EndPoints.GET_INGREDIENTS)
    @Headers("Accept: application/json")
    fun getAllIngredient(): Call<IngredientResponse>

    @GET(EndPoints.GET_DETAIL_INGREDIENTS)
    @Headers("Accept: application/json")
    fun getDetailIngredient(
        @Query("id") id: Int
    ): Call<DetailIngredientResponse>

    @POST(EndPoints.POST_INGREDIENTS)
    @Headers("Accept: application/json")
    fun addIngredient(
        @Query("id") id: Int,
        @Field("name") name: String,
        @Field("image") image: String,
    ): Call<DetailIngredientResponse>

    @POST(EndPoints.UPDATE_INGREDIENTS)
    @Headers("Accept: application/json")
    fun updateIngredient(
        @Query("id") id: Int,
        @Field("name") name: String,
        @Field("image") image: String,
    ): Call<DetailIngredientResponse>

    @POST(EndPoints.DELETE_INGREDIENTS)
    @Headers("Accept: application/json")
    fun deleteIngredient(
        @Query("id") id: Int,
    ): Call<UpdateJamuResponse>

    /**
     * STORE
     * */
    @GET(EndPoints.GET_STORE)
    @Headers("Accept: application/json")
    fun getAllStore(): Call<StoreResponse>

    @GET(EndPoints.GET_DETAIL_STORE)
    @Headers("Accept: application/json")
    fun getDetailStore(
        @Query("id") id: Int
    ): Call<DetailStoreResponse>

    @POST(EndPoints.POST_STORE)
    @Headers("Accept: application/json")
    fun addStore(
        @Header("Authorization") token: String,
        @Field("name") name: String,
        @Field("description") description: String,
        @Field("payment_address") paymentAddress: String,
        @Field("image") image: String,
    ): Call<DetailStoreResponse>

    /**
     * PRODUCTS
     * */
    @GET(EndPoints.GET_PRODUCT)
    fun getAllProduct(): Call<ProductResponse>

    @GET(EndPoints.GET_DETAIL_PRODUCT)
    fun getDetailProduct(
        @Path("id") id: Int
    ): Call<DetailProductResponse>


    /**
     * CART
     * */
    @GET(EndPoints.GET_USER_CART)
    @Headers("Accept: application/json")
    fun getUserCart(
        @Header("Authorization") token: String,
    ): Call<CartResponse>

    @POST(EndPoints.ADD_USER_CART)
    @Headers("Accept: application/json")
    fun addUserCart(
        @Header("Authorization") token: String,
        @Query("product_id") id: Int,
        @Query("quantity") quantity: Int,
    ): Call<AddCartResponse>

    @DELETE(EndPoints.DELETE_USER_CART)
    @Headers("Accept: application/json")
    fun deleteUserCart(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
    ): Call<DeleteCartResponse>

    /**
     * TRANSACTION
     * */
    @GET(EndPoints.CONFIRM_CHECKOUT)
    fun confirmCheckout(
        @Header("Authorization") token: String,
    ): Call<CheckoutResponse>
}