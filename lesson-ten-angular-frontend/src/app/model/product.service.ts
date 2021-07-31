import { Injectable } from '@angular/core';
import {Product} from "./product"
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private identity: number =6;

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'Basic ' + btoa('admin:123')
    })
  };

  private products: {[key: number]: Product} = {
    1: new Product(1, "Test", 17.7),
    2: new Product(2, "Test1", 18),
  }

  constructor(public http: HttpClient) { }

  public findAll() {
    return  this.http.get<Product[]>('/api/v1/product/all', this.httpOptions).toPromise();
    /*
    return new Promise<Product[]>((resolve, reject) => {
      resolve(
        Object.values(this.products)
      )
    })
     */
  }

  public findById(id: number) {
    return this.http.get<Product>(`/api/v1/product/${id}`, this.httpOptions).toPromise();
    /*
    return new Promise<Product>((resolve, reject) => {
      resolve(
        this.products[id]
      )
    })
     */
  }

  public save(product: Product) {
    if (product.id == -1) {
      // @ts-ignore
      delete product.id;
      return this.http.post<Product>('/api/v1/product', product, this.httpOptions).toPromise();
    } else {
      return this.http.put<Product>('/api/v1/product', product, this.httpOptions).toPromise();
    }
    /*
    return new Promise<void>((resolve, reject) => {
      if (product.id == -1) {
        product.id = this.identity++;
      }
      this.products[product.id] = product;
      resolve()
    })
     */
  }

  public delete(id: number) {
    return this.http.delete(`/api/v1/product/${id}`, this.httpOptions).toPromise();
    /*
    return new Promise<void>((resolve, reject) => {
      delete this.products[id];
      resolve()
    })
     */
  }

}
