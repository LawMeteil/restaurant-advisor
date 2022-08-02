<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Str;

class RestaurantSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('restaurant')->insert([
            'name' => 'MacDonalds',
            'description' => 'Classic, long-running fast-food chain known for its burgers & shakes.',
            'grade' => '3.2',
            'localization' => 'Centre Commercial Grand Ciel, 30 Boulevard Paul Vaillant Couturier, 94200 Ivry-sur-Seine',
            'phone_number' => '01 49 60 62 60',
            'website' => 'macdonalds.fr',
            'hours' => 'Monday-Saturday 9AM-9PM, Sunday Closed'
        ]);
    }
}
