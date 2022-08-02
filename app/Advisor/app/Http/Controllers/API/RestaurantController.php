<?php

namespace App\Http\Controllers\API;

use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\Validator;
use App\Models\Restaurant;
use Illuminate\Http\Request;

class RestaurantController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $restaurants = Restaurant::all();

        return response()->json($restaurants);
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
            'name' => 'required|max:100',
            'description' => 'required|max:280',
            'grade' => 'required|max:3',
            'localization' => 'required|max:140',
            'phone_number' => 'required|max:20',
            'website' => 'required|max:100',
            'hours' => 'required|max:100',
        ]);

            $restaurant = new Restaurant;

            $restaurant->name = $request->name;
            $restaurant->description = $request->description;
            $restaurant->grade = $request->grade;
            $restaurant->localization = $request->localization;
            $restaurant->phone_number = $request->phone_number;
            $restaurant->website = $request->website;
            $restaurant->hours = $request->hours;

            $restaurant->save();

            

        if ($validator->fails()){
            return response()->json(['message' => 'Erreur des données.'], 400);
        }

        return response()->json($restaurant, 201);
            
        
         /*$restaurant = Restaurant::create([
            'name' => $request->name,
            'description' => $request->description,
            'grade' => $request->grade,
            'localization' => $request->localization,
            'phone_number' => $request->phone_number,
            'website' => $request->website,
            'hours' => $request->hours,
        ]);*/

        
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Models\User  $user
     * @return \Illuminate\Http\Response
     */
    public function show(Restaurant $restaurant)
    {
        return response()->json($restaurant);
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\User  $user
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, Restaurant $restaurant)
    {
        $validator = Validator::make($request->all(), [
            'name' => 'required|max:100',
            'description' => 'required|max:280',
            'grade' => 'required|max:3',
            'localization' => 'required|max:140',
            'phone_number' => 'required|max:20',
            'website' => 'required|max:100',
            'hours' => 'required|max:100',

        ]);

        $restaurant = Restaurant::create([
            'name' => $request->name,
            'description' => $request->description,
            'grade' => $request->grade,
            'localization' => $request->localization,
            'phone_number' => $request->phone_number,
            'website' => $request->website,
            'hours' => $request->hours,
        ]);

        if ($validator->fails()){
            return response()->json(['message' => 'Erreur des données.'], 400);
        }
        return response()->json(['message' => 'Success'], 200);

    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\User  $user
     * @return \Illuminate\Http\Response
     */
    public function destroy(Restaurant $restaurant)
    {
        $restaurant->delete();

        return response()->json();
    }
}
