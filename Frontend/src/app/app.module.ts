import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { AppComponent } from "./app.component";
import { PlayerService } from "./player.service";
import { HttpClientModule } from '@angular/common/http'; // ✅ Import this!

@NgModule({
  declarations: [AppComponent],
  imports: [BrowserModule, HttpClientModule], // ✅ Add HttpClientModule here
  bootstrap: [AppComponent]
})
export class AppModule {}
