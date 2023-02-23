import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MainFrameComponent } from './main-frame/main-frame.component';
import { MatTabsModule } from '@angular/material/tabs';
import { GatewayComponent } from './gateway/gateway.component';
import { MatTableModule } from '@angular/material/table';

import { ConfigComponent } from './config/config.component';
import { HttpClientModule } from '@angular/common/http';
import { MatNativeDateModule } from '@angular/material/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatToolbarModule } from '@angular/material/toolbar'
import { MatIconModule } from '@angular/material/icon';
import { ApplicationComponent } from './config/application/application.component';
import { AddEditPropertyComponent } from './config/add-edit-property/add-edit-property.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatPaginatorModule } from '@angular/material/paginator';
import { PaginatorDirective } from './directives/matpaginator.directive';


@NgModule({
  declarations: [
    AppComponent,
    MainFrameComponent,
    GatewayComponent,
    ConfigComponent,
    ApplicationComponent,
    AddEditPropertyComponent,
    PaginatorDirective
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatTabsModule,
    HttpClientModule,
    MatNativeDateModule,
    FormsModule,
    ReactiveFormsModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatToolbarModule,
    MatIconModule,
    MatDialogModule,
    MatButtonModule,
    MatPaginatorModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
