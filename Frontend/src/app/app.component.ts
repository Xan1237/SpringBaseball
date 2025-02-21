import { Component, OnInit } from '@angular/core';
import {Player} from './player'
import { PlayerService } from './player.service';
import { HttpErrorResponse } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { response } from 'express';
@Component({
  selector: 'app-root',
  imports: [CommonModule, FormsModule], 
  templateUrl: './app.component.html',
  standalone: true,
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  public players: Player[] | null=null;
  public editPlayer: Player | null = null;
  public deletePlayer: Player | null = null;


  constructor(private playerService: PlayerService){}

  ngOnInit() {
      this.getPlayers();
  }

  public getPlayers(): void{
    this.playerService.getPlayers().subscribe(
      (reponse: Player[]) => {
        this.players = reponse;
      },
      (error: HttpErrorResponse) =>{
        alert(error.message);
      }
    );
  }


  public onAddPlayer(addForm: NgForm): void{
    const addPlayerForm = document.getElementById('add-player-form');
    if (addPlayerForm) {
      addPlayerForm.click();
    }
    this.playerService.addPlayer(addForm.value).subscribe(
      (response: Player) => {
        console.log(response);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  
  public onUpdatePlayer(player: Player): void{
    const addPlayerForm = document.getElementById('add-player-form');
    if (addPlayerForm) {
      addPlayerForm.click();
    }
    this.playerService.updatePlayer(player).subscribe(
      (response: Player) => {
        console.log(response);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


  public onDeletePlayer(playerId: number): void {
    this.playerService.deletePlayer(playerId).subscribe(
      (response: void) => {
        console.log(response);
        this.getPlayers();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onOpenModal(player: Player  | null, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addPlayerModal');
    }
    if (mode === 'edit') {
      if(player){
        this.editPlayer = player;
      }
     
      button.setAttribute('data-target', '#updatePlayerModal');
    }
    if (mode === 'delete') {
      if(player){
        this.deletePlayer = player;
      }
     
      button.setAttribute('data-target', '#deletePlayerModal');
    }
    if(container){
      container.appendChild(button);
    }
    button.click();
  }



}