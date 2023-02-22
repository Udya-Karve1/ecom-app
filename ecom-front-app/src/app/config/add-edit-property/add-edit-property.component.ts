import { Component, Inject, OnInit } from '@angular/core';
import { ConfigService } from 'src/app/services/config.service';
import { Property, PropertyData } from 'src/app/config/ConfigPojo';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormControl, FormGroup } from '@angular/forms';



@Component({
  selector: 'app-add-edit-property',
  templateUrl: './add-edit-property.component.html',
  styleUrls: ['./add-edit-property.component.css']
})
export class AddEditPropertyComponent implements OnInit {

  constructor(private configService: ConfigService
    , @Inject(MAT_DIALOG_DATA) public data: PropertyData) { }


  
  propertyId: string;

  property: Property;
  propertyForm: FormGroup;
  applicationList = []
  profileList = []
  selectedApplicationId: number;


  ngOnInit(): void {
    console.log("init **************");
    this.propertyId = this.data.propertyId;
    this.getAllApplilcation();
    this.getAllProfile();
    this.initializeForm();

    if(this.propertyId) {
      this.configService.getPropertyById(this.propertyId).subscribe((response:any)=>{
        this.property = response;
        this.selectedApplicationId = response.application_id;

        this.propertyForm.patchValue({
          'id': this.property.id,
          'key': this.property.key,
          'value': this.property.value,
          'label': this.property.label,
          'application_id': this.property.application_id,
          'profile_id': this.property.profile_id,
        });
        
      })
    }
  }



  getAllApplilcation() {
    console.log("getAllApplilcation **************");
    this.configService.getAllApplicationList().subscribe((response:any)=>{
      this.applicationList = response;
    });   
  }




  getAllProfile() {
    this.configService.getAllProfileList().subscribe((response:any)=>{
      this.profileList = response;
      console.log(this.profileList);
    });   
  }

  initializeForm() {
    console.log("initializeForm **************");
    this.propertyForm = new FormGroup({
      'id': new FormControl(),
      'key': new FormControl(),
      'value': new FormControl(),
      'label': new FormControl(),
      'application_id': new FormControl(),
      'profile_id': new FormControl(),
    });
    console.log("initializeForm 2 **************");
  }

  saveProperty() {
    alert("");
  }

  addUpdateProperty() {
    console.log('add update property called....')
  }

  applicationChange() {
    
    
  }
}
