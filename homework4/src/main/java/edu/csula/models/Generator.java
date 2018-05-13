package edu.csula.models;

public class Generator {
	private final int id;
	private final String name;
	private final String description;
	private final int rate;
	private final int baseCost;
	private final int unlockAt;
	private int quantity;

	public Generator(int id, String name, String description, int rate, int baseCost, int unlockAt) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.rate = rate;
		this.baseCost = baseCost;
		this.unlockAt = unlockAt;
		this.quantity = 0;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getRate() {
		return rate;
	}

	public int getBaseCost() {
		return baseCost;
	}

	public int getUnlockAt() {
		return unlockAt;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		final Generator generator = (Generator) obj;
		return this.id == generator.id && this.name.equals(generator.name) &&
			this.description.equals(generator.description) &&
			this.baseCost == generator.baseCost &&
			this.rate == generator.rate &&
			this.unlockAt == generator.unlockAt;
	}

	@Override
	public int hashCode() {
		return this.id;
	}
}
