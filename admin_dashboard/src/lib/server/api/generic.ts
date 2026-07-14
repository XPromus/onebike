// import { error } from "@sveltejs/kit";

// export const putRequest = async <InputType, ResponseType>(
//     url: string,
//     input: InputType
// ): Promise<ResponseType> => {
//     const response = await fetch(
//         url, {
//             method: "PUT",
//             headers: {
//                 "Content-Type": "application/json"
//             },
//             body: JSON.stringify(input)
//         }
//     )

//     if (!response.ok) {
//         throw error(response.status, "Failed PUT request.")
//     }

//     const responseData: ResponseType = await response.json();
//     return responseData;
// }
