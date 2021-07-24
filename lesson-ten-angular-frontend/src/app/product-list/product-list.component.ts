import { Component, OnInit } from '@angular/core';
import {ProductService} from "../model/product.service";
import {Product} from "../model/product";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent implements OnInit {

  public products: Product[] = [];
  public isError: boolean = false;

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.retrieveProducts();
  }

  private retrieveProducts() {
    this.productService.findAll()
      .then(res => {
        this.products = res;
        this.isError = false;
      })
      .catch(err => {
        console.log(err)
        this.isError = true;
      })
  }

  delete(id: number) {
    this.productService.delete(id)
      .then(() => {
        this.retrieveProducts();
      })
  }
}
