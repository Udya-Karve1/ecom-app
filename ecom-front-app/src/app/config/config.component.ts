import { Component, OnInit } from '@angular/core';
import { ConfigService } from '../services/config.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatSelectChange } from '@angular/material/select';
import { MatToolbar } from '@angular/material/toolbar';
import { MatIcon } from '@angular/material/icon'

import _ from "lodash";

@Component({
  selector: 'app-config',
  templateUrl: './config.component.html',
  styleUrls: ['./config.component.css']
})
export class ConfigComponent implements OnInit {

  constructor(private configSrvice: ConfigService) { }

  applicationList = []
  dataSource: MatTableDataSource<any>;
  dataSourceFilters: MatTableDataSource<any>;

  propFilters: PropFilter[] = [];
  filterDictionary= new Map<string,string>();
  applications = [];
  isApplication = false;
  isProperty = true;

  displayedColumns = [
    //"id", 
    "key", 
    "value",
    "application",
    "profile",
    "action"
  //  "application_id",
  //  "profile_id"
  ];

  keyMap = {
    'application': 'application_id',
    'profile': 'profile_id'
  }

    defaultValue = 'All';

    
  ngOnInit(): void {
    this.getAllPropertyForApplication();
    
    this.getAllApplilcation();
    this.getAllProfile();
    


  }


  getAllApplilcation() {
    this.configSrvice.getAllApplicationList().subscribe((response:any)=>{
      
      this.applicationList = response;
        
        this.applications.push({
          'name': 'All',
          'value': 'All',
          'isdefault': true
        });

      this.applicationList.forEach(entry =>{
        let appObj = {
          'name': entry.applicationId,
          'value': entry.application,
          'isdefault': false
        }
        this.applications.push(appObj);
      });
      console.log(" ************* ");
      console.log(JSON.stringify(this.applications));

      this.propFilters.push({
        name: 'application',
        options: this.applications,
        defaultValue: this.defaultValue,
      });
      
    });   
  }




  getAllProfile() {
    this.configSrvice.getAllApplicationList().subscribe((response:any)=>{
      this.applicationList = response;
    });   
  }

  getAllPropertyForApplication() {
    this.configSrvice.getAllPropertyForApplication().subscribe((response:any)=>{
      this.dataSource = new MatTableDataSource(response);
      this.dataSourceFilters = new MatTableDataSource(response);

      this.dataSourceFilters.filterPredicate = function (record, filter) {
        //debugger;
        var map = new Map(JSON.parse(filter));
        
        
        let isMatch = false;
        
        for (let [key, value] of map) {
          
          console.log("key>>" + key + ", value>>"+value+ ", record>>" + JSON.stringify(record));
          if(key=='application') {
            key = 'application_id';
          } else if(key=='profile') {
            key = 'profile_id';
          }

          console.log(record[key as keyof Property]);

          isMatch = value == 'All' || record[key as keyof Property] == value;
          //console.log(isMatch)
          //if (!isMatch) return false;
        }
        return isMatch;
      };
  
    })
  }

  applyEmpFilter(ob: MatSelectChange, empfilter: PropFilter) {

    console.log(empfilter.name+"<<>>"+ob.value);
    if(empfilter.name=='application') {
      empfilter.name = 'application_id';
    } else if(empfilter.name=='profile') {
      empfilter.name = 'profile_id';
    }
    this.filterDictionary.set(empfilter.name, ob.value);

    var jsonString = JSON.stringify(
      Array.from(this.filterDictionary.entries())
    );

    console.log('jsonString>> ' + jsonString);

    this.dataSourceFilters.filter = jsonString;
    //console.log(this.filterValues);
  }

  applyFilter(event: Event) {
    alert(event);
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }


  editProperty(propertyId) {
    alert("Edit property " + propertyId);
  }

  deleteProperty(propertyId) {
    alert("Delete property " + propertyId);
  }

  appManagement() {
    this.isApplication = true;
    this.isProperty = false;
  }
}


export interface PropFilter {
  name:string;
  options: any;
  defaultValue:string;
}

export interface FilterOption {
  name:string;
  value:string;
  isdefault:boolean;
}


export interface Property {
  id : number,	
  key:string,	
  value:string,	
  application_id:number,
  profile_id:number,	
  profile:string,
  application:string
}
