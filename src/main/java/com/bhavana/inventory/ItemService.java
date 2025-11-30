
package com.bhavana.inventory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemService {
    private final ItemRepository repo;

    public ItemService(ItemRepository repo) { this.repo = repo; }

    public List<Item> all() { return repo.findAll(); }

    public Item get(Long id) { return repo.findById(id).orElseThrow(() -> new NotFoundException("Item not found: " + id)); }

    public Item create(Item item) { return repo.save(item); }

    public Item update(Long id, Item input) {
        Item existing = get(id);
        existing.setSku(input.getSku());
        existing.setName(input.getName());
        existing.setQuantity(input.getQuantity());
        existing.setPrice(input.getPrice());
        return repo.save(existing);
    }

    public void delete(Long id) { repo.delete(get(id)); }

    public Item adjustQuantity(Long id, int delta) {
        Item existing = get(id);
        int newQty = existing.getQuantity() + delta;
        if (newQty < 0) throw new IllegalArgumentException("Quantity cannot be negative");
        existing.setQuantity(newQty);
        return repo.save(existing);
    }
}
