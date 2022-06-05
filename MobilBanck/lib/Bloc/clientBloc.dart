import 'package:bloc/bloc.dart';
import 'package:dejitalbancking/model/client.dart';

import '../reposetory/blocReosetory.dart';
import 'ClientEvent.dart';
import 'clientState.dart';

class CustomerBloc extends Bloc<CustomerEvent, CustomerState> {
  CustomerRepository customerRepository;
  CustomerBloc(CustomerState customerState, this.customerRepository) : super(CustomerInitial(customers: [], errorMessage: '', requested: Requested.Loading)) {
    on<CustomerEvent>((event, emit) async {
      if (event is ListCustomerEvent ){
        try {
          emit(CustomerState(
              customers: [], errorMessage: '', requested: Requested.Loading));
          List<Clientt  >contacts = await customerRepository.getallCustomers();
          print(contacts);
          emit(CustomerState(
              customers: contacts,
              errorMessage: '',
              requested: Requested.Loaded));
        } catch (e) {
          emit(CustomerState(
              customers: [],
              errorMessage: e.toString(),
              requested: Requested.Error));
        }
      }
    });
  }
}