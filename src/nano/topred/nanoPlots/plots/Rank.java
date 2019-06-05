package nano.topred.nanoPlots.plots;
    public enum Rank {
        BANNED,
        RANDOM,
        PLAYER,
        TRUSTED,
        OWNER
                {
                    @Override
                    public Rank next() {
                        return this; // see below for options for this line
                    }

                    @Override
                    public Rank previous() {
                        return this; // see below for options for this line
                    }
                };
        public Rank next() {
            // No bounds checking required here, because the last instance overrides
            return values()[ordinal() + 1];
        }
        public Rank previous() {
            // No bounds checking required here, because the last instance overrides
            return values()[ordinal() - 1];
        }

    }

