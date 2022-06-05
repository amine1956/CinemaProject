import 'package:dejitalbancking/model/client.dart';

enum  Requested{Loaded,Loading,Error,NONE}
class CustomerState {
  List<Clientt> customers;
  Requested requested;
  String errorMessage;
  CustomerState({required this.requested, required this.customers,required this.errorMessage});
}

class CustomerInitial extends CustomerState {
  CustomerInitial({required Requested requested, required List<Clientt> customers, required String errorMessage}) : super(requested: requested, customers: customers, errorMessage: errorMessage);
}