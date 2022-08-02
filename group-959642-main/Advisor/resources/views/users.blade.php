<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
@foreach($user ?? '' as $key => $data)
    <tr>    
      <th>{{$data->username}}</th>
      <th>{{$data->name}}</th>
      <th>{{$data->firstname}}</th>
      <th>{{$data->age}}</th>                 
    </tr>
@endforeach
</body>
</html>