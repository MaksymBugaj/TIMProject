import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'search'
})
export class SearchPipe implements PipeTransform {

  transform(value: any, args: any, term: any): any {
    if(!term) return value;
    return (value || []).filter((item) => args.split(',').some(arg => item.hasOwnProperty(arg) && new RegExp(term, 'gi').test(item[arg])));
  }

}
