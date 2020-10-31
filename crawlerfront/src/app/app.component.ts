import { Component, OnInit } from '@angular/core';
import { DemandserviceService } from './demandservice.service';
import { ResponseDTO } from './DTO/ResponseDTO';
import { DemandDetailDTO } from './DTO/DemandDetailDTO';
import { FormGroup, FormControl, Validators } from '@angular/forms'; 

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {

  title = 'crawlerfront';

  resp: ResponseDTO;
  demands: DemandDetailDTO[];
  idSearchForm:FormGroup;
  hayDatos: Boolean = false;

  headers = ["Nombre", "Locación", "Versus", "Número", "Actor", "Demandado"];

  ngOnInit() {
    this.idSearchForm = new FormGroup({
      'searchDemand': new FormControl('', [
        Validators.required
      ])
    });
  }

  constructor(private demandService: DemandserviceService) {
  }

  getDemandsByName() {

    let nameSearch = this.idSearchForm.controls.searchDemand.value;
    console.log("Valor del input: "+nameSearch);

    this.demandService.getDemandsByName(nameSearch).subscribe(value => { 
        this.resp = value;
       
        if(this.resp.status) {
        
          this.demands = this.resp.object as DemandDetailDTO[];
          this.hayDatos = true;

        }
        
      }
    );
  }

}


