import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { File }         from '../file';
import { FileService }  from '../file.service';

@Component({
  selector: 'app-popup',
  templateUrl: './popup.component.html',
  styleUrls: ['./popup.component.css']
})
export class PopupComponent implements OnInit {
image: File;

  constructor(
    private route: ActivatedRoute,
    private fileService: FileService,
    private location: Location
  ) { }

  ngOnInit(): void {
  //  this.getImage();
  }

  // getImage(): void {
  //   const name = +this.route.snapshot.paramMap.get('name');
  //   this.fileService.getFile(name)
  //     .subscribe(image => this.image = image);
  // }

  goBack(): void {
    this.location.back();
  }
}





 