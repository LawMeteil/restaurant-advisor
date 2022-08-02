<?php

namespace App\Http\Controllers\API;

use App\Http\Controllers\Controller;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Hash;
use Laravel\Sanctum\HasApiTokens;

class UserController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $users = User::all();

        return response()->json($users, 200);
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $validator = Validator::make($request->all(), [
            'username' => 'required|max:100',
            'name' => 'required|max:100',
            'firstname' => 'required|max:100',
            'age' => 'required|integer'
        ]);

        $user = User::create([
            'login' => $request->login,
            'password' => Hash::make($request->password),
            'email' => $request->email,
            'name' => $request->name,
            'firstname' => $request->firstname,
            'age' => $request->age,
        ]);

        $token = $user->createToken('token')->plainTextToken;

        $reponse = [
            'user' => $user,
            'token' => $token,
        ];

        return response()->json($user, 201);

    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Models\User  $user
     * @return \Illuminate\Http\Response
     */
    public function show(User $user)
    {
        return response()->json($user);
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\User  $user
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, User $user)
    {
        $validator = Validator::make($request->all(), [
            'username' => 'required|max:100',
            'name' => 'required|max:100',
            'firstname' => 'required|max:100',
            'age' => 'required|max:3'
        ]);

        $user = User::create([
            'username' => $request->username,
            'name' => $request->name,
            'firstname' => $request->firstname,
            'age' => $request->age,
        ]);

        if ($validator->fails()){
            return response()->json(['message' => 'Erreur des données.'], 400);
        }
        
        return response()->json();
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\User  $user
     * @return \Illuminate\Http\Response
     */
    public function destroy(User $user)
    {
        $user->delete();

        return response()->json();
    }
}
