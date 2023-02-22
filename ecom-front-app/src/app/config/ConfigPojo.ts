
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
    'id' : number,	
    'key':string,	
    'value':string,	
    'application_id':number,
    'profile_id':number,	
    'label':string,
    
  }

export interface PropertyData {
    propertyId: string
}
  