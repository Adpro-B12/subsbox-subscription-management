document.addEventListener("DOMContentLoaded", function() {
    fetchBoxes();
});

function fetchBoxes() {
    const minPrice = document.getElementById('minPrice').value || undefined;
    const maxPrice = document.getElementById('maxPrice').value || undefined;
    const boxName = document.getElementById('boxName').value || undefined;

    let url = '/api/subscriptions/all';
    const params = [];
    if (minPrice !== undefined && maxPrice !== undefined) {
        params.push(`minPrice=${minPrice}`);
        params.push(`maxPrice=${maxPrice}`);
    }
    if (boxName !== undefined) {
        params.push(`name=${boxName}`);
    }
    if (params.length > 0) {
        url += '?' + params.join('&');
    }

    fetch(url)
        .then(response => response.json())
        .then(boxes => displayBoxes(boxes))
        .catch(error => console.error('Error fetching subscription boxes:', error));
}

function displayBoxes(boxes) {
    const container = document.getElementById('boxesContainer');
    container.innerHTML = ''; // Clear previous contents
    boxes.forEach(box => {
        const boxElement = document.createElement('div');
        boxElement.className = 'box';
        boxElement.innerHTML = `
            <h2>${box.name}</h2>
            <p>Type: ${box.type}</p>
            <p>Price: ${box.price} IDR</p>
            <ul>
                ${box.items.map(item => `<li>${item.name} (Quantity: ${item.quantity})</li>`).join('')}
            </ul>
        `;
        container.appendChild(boxElement);
    });
}

function applyFilters() {
    fetchBoxes();
}
