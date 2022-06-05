import 'dart:convert';
import 'package:http/http.dart' as http;

import '../model/client.dart';
class CustomerRepository{


  Future<List<Clientt>>  getallCustomers() async {
    final response=await  http.get(Uri.parse("http://localhost:8088/clients"));
    if (response.statusCode == 200) {
      // If the server did return a 200 OK response,
      // then parse the JSON.
      final customers = List<dynamic>.from(
        json.decode(response.body).map<dynamic>(
              (dynamic item) => item,
        ),
      );
      List<Clientt> cut=customers.map((e) => Clientt.fromJson(e)).toList();

      return cut;
    } else {
      // If the server did not return a 200 OK response,
      // then throw an exception.
      throw Exception('Failed to load album');
    }
  }






}