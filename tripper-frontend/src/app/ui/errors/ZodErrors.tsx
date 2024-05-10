export function ZodErrors({ error }: { error: string[] }) {
  if (!error) return null;
  return error.map((err: string, index: number) => (
    <div key={index} className="text-error text-xs italic py-2">
      {err}
    </div>
  ));
}
