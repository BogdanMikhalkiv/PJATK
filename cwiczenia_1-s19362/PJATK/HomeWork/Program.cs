using System;
using System.Net.Http;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace HomeWork {
    class Program {
        public static bool IsUrlValid(string url) {
            return Regex.IsMatch(url, @"(http|https)://([\w-]+\.)+[\w-]+(/[\w- ./?%&=]*)?");
        }
        static async Task Main(string[] args) {
            if (args.Length == 0) {
                throw new ArgumentNullException();
            }

            String url = "";
            try
            {
                url = args[0];
                HttpClient httpClient = new HttpClient();
                HttpResponseMessage message = await httpClient.GetAsync(url);
            
                message.EnsureSuccessStatusCode();
                string responseBody = await message.Content.ReadAsStringAsync();
            
                string pattern = @"(\b\w+@[a-zA-Z_\\.\\-]+\b)";  
                Regex rg = new Regex(pattern);  
  
                MatchCollection matchedAuthors = rg.Matches(responseBody);
                if (matchedAuthors.Count == 0)  {
                    Console.WriteLine("Nie znaleziono adresów email");
                }
                else
                {
                    for (int count = 0; count < matchedAuthors.Count; count++)  
                        Console.WriteLine(matchedAuthors[count].Value);    
                }

                
                httpClient.Dispose();
            }
            catch (HttpRequestException e)
            {
                Console.WriteLine("Błąd w czasie pobierania strony");
                throw new ArgumentException();
            }









        }
      
    }
}