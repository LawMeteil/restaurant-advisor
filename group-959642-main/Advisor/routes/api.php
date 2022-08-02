<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

use App\Http\Controllers\API\UserController;
use App\Http\Controllers\API\RestaurantController;
use App\Http\Controllers\API\MenuController;
use App\Http\Controllers\API\UtilisateurController;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});

// Route::apiResource("users", UserController::class);

// Route::apiResource("restaurants", RestaurantController::class);

Route::get('/restaurants', [RestaurantController::class, 'index']);

Route::post('/restaurant', [RestaurantController::class, 'store']);

// Route::put('/restaurant/{id}', [RestaurantController::class, 'update']);

// Route::delete('/restaurant/{id}', [RestaurantController::class, 'destroy']);

// Route::get('/restaurant/{id}/menus', [MenuController::class, 'index']);

// Route::post('/restaurant/{id}/menu', [MenuController::class, 'store']);

// Route::put('/restaurant/{id}/menu/{id}', [MenuController::class, 'update']);

// Route::delete('/restaurant/{id}/menu{id}', [MenuController::class, 'destroy']);

// Route::apiResource("restaurant/menus", MenuController::class);

Route::get('/users', [UserController::class, 'index']);  

Route::post('/register', [UserController::class, 'store']);

Route::post('/auth', [UtilisateurController::class, 'login']);