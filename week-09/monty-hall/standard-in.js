// prompt-sync exports a create function
import createPrompt from "prompt-sync";
// create a new prompt function with the create function.
const prompt = createPrompt();

// Pass-through function.
// Ensures that prompt-sync doesn't "leak" outside our module.
export function readString(message) {
    return prompt(message);
}

// Prompts the user for an integer between min and max.
// Re-prompts if the input is not a number 
// or is not in the valid range.
export function readInt(message, min, max) {

    let valid = false;
    let result;

    do {
        let input = readString(message);
        result = parseInt(input, 10);
        if (isNaN(result)) {
            console.log(`'${input}' is not a number.`)
        } else if (result < min || result > max) {
            console.log(`Value must be between ${min} and ${max}.`);
        } else {
            valid = true;
        }
    } while (!valid);

    return result;
}