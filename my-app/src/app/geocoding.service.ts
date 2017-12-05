import { createClient, GoogleMapsClient } from '@google/maps';
declare const google: any;
export class GeoCodingService {

  private geoCoder: GoogleMapsClient;
    
  public constructor() {
    this.geoCoder = createClient({
      key: 'AIzaSyBLJYr-yAzmfkn1dmyeeFALAl7BxeWnnxs',
    });

  }

  public geocodeAddress(_address): Promise<google.maps.GeocoderResult[]> {
    const request: google.maps.GeocoderRequest = {
      address: _address
    };

    return new Promise<google.maps.GeocoderResult[]>((resolve, reject) => {
      this.geoCoder.geocode(request, (error, response) => {

        if (error) {
          reject(error);
        }

        resolve(response.json.results);

      });
    });

  }
}