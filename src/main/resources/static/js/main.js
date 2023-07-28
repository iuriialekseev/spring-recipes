window.addEventListener('DOMContentLoaded', (event) => {
    const addRecipeProductButton = document.querySelector("#add-recipe-product");
    if (addRecipeProductButton) {
        addRecipeProductButton.addEventListener("click", addRecipeProduct);
    }
});

function addRecipeProduct() {
    const recipeProductContainer = document.getElementById("recipe-product-container");
    const recipeProductTemplate = document.getElementById("recipe-product-template");
    const nextRecipeProductIndex = recipeProductContainer.children.length;
    const newRecipeProductDiv = recipeProductTemplate.cloneNode(true);

    newRecipeProductDiv.removeAttribute("style");
    newRecipeProductDiv.removeAttribute("id");

    const recipeProductProduct = newRecipeProductDiv.getElementsByClassName("recipe-product-product")[0];
    const recipeProductAmount = newRecipeProductDiv.getElementsByClassName("recipe-product-quantity")[0];
    recipeProductProduct.setAttribute("name", `recipeProducts[${nextRecipeProductIndex}].product`);
    recipeProductAmount.setAttribute("name", `recipeProducts[${nextRecipeProductIndex}].quantity`);

    recipeProductContainer.appendChild(newRecipeProductDiv);
}