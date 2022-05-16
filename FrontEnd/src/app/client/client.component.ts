import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ClientserviceService} from "../services/clientservice.service";
import {catchError, Observable, throwError} from "rxjs";
import {Client} from "../model/client.model";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit {
  errorMessage: Object |undefined;
  clients!:Observable<Array<Client>>;
  searchformGroup:FormGroup |undefined;
  constructor(private clientservice:ClientserviceService,private fb :FormBuilder) { }

  ngOnInit(): void {
    this.searchformGroup=this.fb.group({
     keyword:this.fb.control("")
    });
  this.clients=this.clientservice.getClient().pipe(catchError(err => {
    this.errorMessage=err.message;
    return throwError(err);
  }));

  }

  handeleSeaechClient() {
let kw=this.searchformGroup?.value.keyword;
    this.clients=this.clientservice.SearchClient(kw).pipe(catchError(err => {
      this.errorMessage=err.message;
      return throwError(err);
    }));
  }
}
