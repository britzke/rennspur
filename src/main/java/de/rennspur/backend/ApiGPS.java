package de.rennspur.backend;

public class ApiGPS {
	public void receivePost{
		 HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 

		    try {
		        HttpPost request = new HttpPost("http://yoururl");
		        StringEntity params = new StringEntity("");
		        request.addHeader("content-type", "application/x-www-form-urlencoded");
		        request.setEntity(params);
		        HttpResponse response = httpClient.execute(request);

		        // handle response here...
		    }catch (Exception ex) {
		        // handle exception here
		    } finally {
		        httpClient.getConnectionManager().shutdown(); //Deprecated
		    }
	}
	
	public void parseJson(){
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader("c:\\test.json"));

			JSONObject jsonObject = (JSONObject) obj;

			String name = (String) jsonObject.get("name");
			System.out.println(name);

			long age = (Long) jsonObject.get("age");
			System.out.println(age);

			// loop array
			JSONArray msg = (JSONArray) jsonObject.get("messages");
			Iterator<String> iterator = msg.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}