<?php

namespace App\Http\Controllers\API;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Laravel\Sanctum\HasApiTokens;
use App\Models\User;

class UtilisateurController extends Controller
{

    function login(Request $request)
    {
        $request->validate([
            'login' => 'required',
            'password' => 'required',
        ]);

        $user = request(['login', 'password']);
        if (!Auth::attempt($user)) {
            return response()->json(['message' => 'Invalid login'], 400);
        }

        $token = auth()->user()->createToken('token')->plainTextToken;
        $user = auth()->user();
        $reponse = [
            'user' => $user,
            'token' => $token,
        ];

        return response()->json(['token' => $token, 'user' => $user], 200);
    }
}
